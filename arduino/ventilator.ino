#define         MG_PIN                       (0)     //define which analog input channel you are going to use
#define         BOOL_PIN                     (2)
#define         DC_GAIN                      (8.5)   //define the DC gain of amplifier

#define         READ_SAMPLE_INTERVAL         (50)    //define how many samples you are going to take in normal operation
#define         READ_SAMPLE_TIMES            (5)     //define the time interval(in milisecond) between each samples in 

#define         ZERO_POINT_VOLTAGE           (0.220) //define the output of the sensor in volts when the concentration of CO2 is 400PPM
#define         REACTION_VOLTGAE             (0.020) //define the voltage drop of the sensor when move the sensor from air into 1000ppm CO2

int PinA1 = 4;
int PinA2 = 5;
int speedA = 9;

int PinB1 = 6;
int PinB2 = 7;
int speedB = 10;

int msgDelay=500;//BT濡� �꽱�떛媛� 蹂대궡�뒗 媛꾧꺽 �씪�떒 5珥�(�떆�뿰�쓣 �쐞�빐)
int CO2SignalPin = A0;
int avr_percentage;
int percentage;//co2 �냽�룄
float volts;//co2 �꽱�꽌 蹂쇳듃

float CO2Curve[3]  =  {2.602,ZERO_POINT_VOLTAGE,(REACTION_VOLTGAE/(2.602-3))};  

void setup() {

  Serial.begin(9600);
  
  
  pinMode(PinA1, OUTPUT);
  pinMode(PinA2, OUTPUT);
  pinMode(speedA, OUTPUT);
  
  pinMode(PinB1, OUTPUT);
  pinMode(PinB2, OUTPUT);
  pinMode(speedB, OUTPUT);

  runboth();
  normalA();
  normalB();
}

int sendControl = 0;
int sendState=0;
int aSpeed=100;
int bSpeed=100;
int speedState=0;
void loop() {
  Serial.print("A"+String(aSpeed, DEC)+"/B"+String(bSpeed, DEC)+"\n");
 /*
  1 - A硫덉땄
  2- B硫덉땄
  3- A瑜� �쐞�뿕�젅踰�1�떒怨꾨줈
  4- B瑜� �쐞�뿕�젅踰�1�떒怨꾨줈
  5- A瑜� �쐞�뿕�젅踰�2�떒怨꾨줈
  6- B瑜� �쐞�뿕�젅踰�2�떒怨꾨줈
  7- A瑜� normal�떒怨꾨줈
  8- B瑜� normal�떒怨꾨줈
// */
  if(Serial.available()>0){
    sendControl= Serial.read();
    if(aSpeed>0 || bSpeed>0){
      if(sendControl==117){
        aSpeed=aSpeed+50;
        bSpeed=bSpeed+50;
        analogWrite(speedA, aSpeed);
        analogWrite(speedB, bSpeed);
      }else if(sendControl==118){
        aSpeed=aSpeed-50;
        bSpeed=bSpeed-50;
        analogWrite(speedA, aSpeed);
        analogWrite(speedB, bSpeed);
      }else if(sendControl==119){//w
        aSpeed=aSpeed+50;
         analogWrite(speedA, aSpeed);
        Serial.write("\n\n119\n\n");
      }else if(sendControl==120){//x
        aSpeed=aSpeed-50; 
         analogWrite(speedA, aSpeed);
          Serial.write("\n\n120\n\n");
      }else if(sendControl==121){//y
        bSpeed=bSpeed+50; 
        
          Serial.write(bSpeed+"속도");
        analogWrite(speedB, bSpeed);
      }else if(sendControl==122){//정지
        bSpeed=bSpeed-50;
          Serial.write(bSpeed+"속도");
        analogWrite(speedB, bSpeed); 
      }
    }
  }
  printDensity();
  if(speedState==1&&percentage<500){
    normalA();
    normalB();
    speedState=0;
  }
  else if(speedState==0&&percentage>=500&&percentage<1000){
    lev2A();
    lev2B();
    speedState=1;
  }else if(percentage>=1000&&percentage<10000){
    lev2A();
    lev2B();
  }

}
void runboth(){
  digitalWrite(PinA1, HIGH);
  digitalWrite(PinA2, LOW);
  digitalWrite(PinB1, HIGH);
  digitalWrite(PinB2, LOW);
}
void stopA(){
  analogWrite(speedA, 0);
}
void stopB(){
  analogWrite(speedB, 0);
}
void normalA(){
  aSpeed=100;
  analogWrite(speedA, 100);
}
void normalB(){
  bSpeed=100;
  analogWrite(speedB, 100);
}
void lev1A(){
  analogWrite(speedA, 170);
}
void lev1B(){
  
  analogWrite(speedB, 170);
}
void lev2A(){
  aSpeed=255;
  analogWrite(speedA, 255);
}
void lev2B(){
  bSpeed=255;
  analogWrite(speedB, 255);
}
float MGRead(int mg_pin)//蹂쇳듃 怨꾩궛
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

int  MGGetPercentage(float volts, float *pcurve)//ppm怨꾩궛
{
   if ((volts/DC_GAIN )>=ZERO_POINT_VOLTAGE) {
      return -1;
   } else { 
      return pow(10, ((volts/DC_GAIN)-pcurve[1])/pcurve[2]+pcurve[0]);
   }
}

//�떆由ъ뼹�뿉 co2�냽�룄 異쒕젰
void printDensity(){

    int sum=0;
    
    volts = MGRead(MG_PIN);

    delay(msgDelay);
    percentage = MGGetPercentage(volts,CO2Curve);
    if (percentage == -1) {
        Serial.print("v:"+(String)volts+"CO2:<400 ppm\n" );
    } else {
        Serial.print("CO2:"+(String)percentage+" ppm\n");
    }
    delay(msgDelay);
}

