package ddnas.web.model.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ddnas.web.model.dao.DDDAO;
import ddnas.web.model.dto.DDDTO;

@Service
public class DDServiceImpl implements DDService {

	@Autowired
	private DDDAO dDDAOImpl;

	@Override
	public List<DDDTO> selectDDList() {
		return dDDAOImpl.selectDDList();
	}

	@Override
	public String insertDD(DDDTO ddDTO, String path) {

		int seq = dDDAOImpl.getSequence();
		ddDTO.setDdNote(ddDTO.getDdNote().replace("\r\n", "<BR/>"));
		ddDTO.setDdCode(seq);
		if (ddDTO.getFile() != null) {
			MultipartFile mf = ddDTO.getFile();

			File file = new File(path + "dd" + seq + ".jpg");

			try {
				if (!mf.getOriginalFilename().equals(""))
					mf.transferTo(file);
			} catch (Exception e) {
			}

			if(dDDAOImpl.insertDD(ddDTO)>0)
				return "redirect:../dd/regists";
			else
				return "redirect:../error/errorMessage";
		}
		return "redirect:../error/errorMessage";
	}

	@Override
	public int deleteDD(int ddCode, String realPath) {
		File file = new File(realPath + "dd"+ddCode+".jpg");
		if (file.exists() == true) {
			file.delete();
		}
		return dDDAOImpl.deleteDD(ddCode);
	}
}
