#include <Wire.h>
#include <LiquidCrystal_I2C.h>//lcd 라이브러리 호출
#include <SoftwareSerial.h> //시리얼통신 라이브러리 호출
#define         MG_PIN                       (0)     //define which analog input channel you are going to use
#define         BOOL_PIN                     (2)
#define         DC_GAIN                      (8.5)   //define the DC gain of amplifier

#define         READ_SAMPLE_INTERVAL         (50)    //define how many samples you are going to take in normal operation
#define         READ_SAMPLE_TIMES            (5)     //define the time interval(in milisecond) between each samples in 

#define         ZERO_POINT_VOLTAGE           (0.220) //define the output of the sensor in volts when the concentration of CO2 is 400PPM
#define         REACTION_VOLTGAE             (0.020) //define the voltage drop of the sensor when move the sensor from air into 1000ppm CO2

int blueTx=2;   //Tx (보내는핀 설정)at
int blueRx=3;   //Rx (받는핀 설정)
int Buzzer = 9; //buzzer 핀 설정
int CO2SignalPin = A0;

int athenNumber=1;
int inputNumber=0;
String myString="";
int lev1min=500;//이산화탄소 농도값만 조절하면 됨!
int lev2min=550;
int msgDelay=1000;//BT로 센싱값 보내는 간격 일단 5초(시연을 위해)

int flag=0;
int flag1=0;

int percentage;//co2 농도
float volts;//co2 센서 볼트
float v1;

byte buffer[1024]; // 데이터를 수신 받을 버퍼

float CO2Curve[3]  =  {2.602,ZERO_POINT_VOLTAGE,(REACTION_VOLTGAE/(2.602-3))};  

LiquidCrystal_I2C lcd(0x27,16,2);// set the LCD address to 0x27 for a 16 chars and 2 line display
SoftwareSerial BTSerial(blueTx, blueRx);  //블루투스 모듈 접속

void setup()
{
  
  //인증번호 생성 및 시리얼 출력
  randomSeed(analogRead(0));
  athenNumber = random(1000,9999);

  Serial.begin(9600);//시리얼 입출력 사용 전 호출해야함
  BTSerial.begin(9600);//블루투스 포트 통신 속도 설정

  pinMode(Buzzer,OUTPUT); //buzzer set
  pinMode(BOOL_PIN, INPUT);                        //set pin to input
  digitalWrite(BOOL_PIN, HIGH);                    //turn on pullup resistors
  
  lcd.init();//lcd 초기화
  lcd.backlight();

  lcd.print("This is DD.");
  delay(1000);
  lcd.init();
   
  }
/////////////////////////////////////////LOOP///////////////////////////////////////////////////
void loop()
{
  if(athenNumber!=inputNumber){
    
    while(BTSerial.available())  //mySerial 값이 있으면
  {
    char myChar = (char)BTSerial.read();  //mySerial int형식의 값을 char형식으로 변환
    myString+=myChar;   //수신되는 문자열을 myString에 모두 붙임 (1바이트씩 전송되는 것을 모두 붙임)
    delay(5);           //수신 문자열 끊김 방지
    
  }
  if(!myString.equals("")){
    inputNumber = myString.toInt();
  }
    myString="";
    
    lcd.print("athen number");
    lcd.setCursor(0,1);
    lcd.print(athenNumber);//인증번호 lcd에 출력
    
    lcd.init();
  }

   //flag1=1;
    
    percentage = MGGetPercentage(volts,CO2Curve);
    volts = MGRead(MG_PIN);
    v1 = 1200-(volts*650);//dd2
    //v1 = 900-(volts*120); //dd1
 
  if(inputNumber==athenNumber){
   
    if(flag==0){
      lcd.print("Paired!");
      lcd.init();
      flag=1;
    }

      lcd.setCursor(0,1);
      lcd.print(v1);
      lcd.print("ppm  ");
      lcd.print(volts);
      lcd.print("V");
      
   //printDensity();//serial에 co2농도 출력 -test 용
   
    BTsendDensity();//블루투스로 농도전송

    if(v1<=lev1min) noTone(Buzzer);//농도가 기준치 이하면 버저 안울려
    
    else if(v1>lev1min&&v1<=lev2min){//경고수준
      alarmLev1();
    }
    
    else if(v1>lev2min){//위험 수준
      alarmLev2();
    }

    if(BTSerial.read()=='z'){
      inputNumber=0;
       flag1=0;
      randomSeed(analogRead(0));
       athenNumber = random(1000,9999);
    }
  }
}

float MGRead(int mg_pin)//볼트 계산
{
    int i;
    float v=0;

    for (i=0;i<READ_SAMPLE_TIMES;i++) {
        v += analogRead(mg_pin);
        delay(READ_SAMPLE_INTERVAL);
    }
    v = (v/READ_SAMPLE_TIMES) *5/1024 ;
    return v;  
}

int  MGGetPercentage(float volts, float *pcurve)//ppm계산
{
   if ((volts/DC_GAIN )>=ZERO_POINT_VOLTAGE) {
      return -1;
   } else { 
      return pow(10, ((volts/DC_GAIN)-pcurve[1])/pcurve[2]+pcurve[0]);
   }
}

//블루투스로 co2농도 전송
void BTsendDensity(){
    
  String ppm = String(v1);
  
  BTSerial.println(ppm); 
  
  delay(msgDelay);//msg보낼 주기만큼
}

//시리얼에 co2농도 출력
void printDensity(){
        
    Serial.print(volts); 
    Serial.print( "V           " );
    
    Serial.print("CO2:");
    if (percentage == -1) {
        Serial.print( "<400" );
    } else {
        Serial.print(percentage);
    }
    Serial.print( "ppm" );  
    Serial.print("\n");
    
    delay(msgDelay);
}
//경고수준 알람
void alarmLev1(){
  tone(Buzzer,100); 
  delay(700); 
  tone(Buzzer,200); 
  delay(700); 
  tone(Buzzer,300); 
  delay(700); 
  tone(Buzzer,400); 
  delay(700);
}
//위험수준 알람
void alarmLev2(){
  tone(Buzzer,150); 
  delay(400); 
  tone(Buzzer,300); 
  delay(400); 
  tone(Buzzer,450); 
  delay(400); 
  tone(Buzzer,600); 
  delay(500); 
}
