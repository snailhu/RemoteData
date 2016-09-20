package DataAn.wordManager.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import DataAn.wordManager.config.OptionConfig;
import DataAn.wordManager.domain.Doc;

@Controller
public class WordToHtmlController {	
	
	
	@RequestMapping(value = { "/secondStyle/wordshow" }, method = { RequestMethod.GET })
	public ModelAndView doc2html(
			@RequestParam(value = "file", required = true)  final String file,
			HttpServletRequest request, HttpServletResponse response) throws Exception,
			FactoryConfigurationError {		
		//web 路径
		String path = OptionConfig.getWebPath();
		String file_name=URLDecoder.decode(file, "UTF-8");//解码   解为中文
		String fileName= new String(file.getBytes("ISO-8859-1"),"UTF-8"); 
		
		ModelAndView modelAndView = new ModelAndView("/secondStyle/wordshow");
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		InputStream input = new FileInputStream(path + "\\report\\飞轮报告1.doc");
		HWPFDocument wordDocument = new HWPFDocument(input);
		WordToHtmlConverter wordToHtmlConverter;
		wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		final String webPath = request.getContextPath();
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			public String savePicture(byte[] content, PictureType pictureType,
					String suggestedName, float widthInches, float heightInches ) {
				return "image\\"
				+ suggestedName;				
			}
		});
		wordToHtmlConverter.processDocument(wordDocument);
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				try {
					/*pic.writeImageContent(new FileOutputStream(path + file
							+ "_" + pic.suggestFullFileName()));*/
					pic.writeImageContent(new FileOutputStream(path+"\\image\\" 
							 + pic.suggestFullFileName()));			
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		Document htmlDocument = wordToHtmlConverter.getDocument();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(outStream);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		outStream.close();

		String content = new String(outStream.toByteArray());
		modelAndView.addObject("content", content);
		return modelAndView;
	}
}
