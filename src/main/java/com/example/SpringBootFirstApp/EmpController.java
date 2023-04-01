package com.example.SpringBootFirstApp;

import com.example.SpringBootFirstApp.Helper.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class EmpController
{
    @Autowired
    EmpRepo empRepo;
    @Autowired
    FileUploader uploader;

    @GetMapping("/")
    public String showdata()
    {

        return "index";
    }
    @GetMapping("/home/")
    public String show(Model model,Employee emp)
    {
        int curPage=1;
        Pageable pageable = PageRequest.of(curPage-1,maxSize, Sort.by("id").descending());
        Page<Employee> page = empRepo.findAll(pageable);
        int totalPages=page.getTotalPages();
        List<Employee> emplist= page.toList();


        model.addAttribute("emplist",emplist);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("curPage",curPage);
        return "EmpReg";
    }

    @PostMapping("/emp/")
    public String showemps(Model model, Employee emp, MultipartFile file)
    {
        int curPage=1;

        String fileNameOld=file.getOriginalFilename();
        String extension=fileNameOld.substring(fileNameOld.indexOf(".")+1);
        emp.setExt(extension);
        Employee empNew = empRepo.save(emp);
        String fileNameNew= empNew.getId()+"."+extension;

        System.out.println("Image  Name new"+ fileNameNew);
        uploader.uploadFile(file,fileNameNew);


        Pageable pageable = PageRequest.of(curPage,maxSize, Sort.by("id").descending());
        Page<Employee> page = empRepo.findAll(pageable);
        int totalPages=page.getTotalPages();
        List<Employee> emplist= page.toList();


        model.addAttribute("emplist",emplist);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("curPage",curPage);
        model.addAttribute("msg", "Employee Registration Successful");

        return "EmpReg";
    }
    @GetMapping("/emp/delete/{empID}/")
    public String SubmitData(Model model, @PathVariable long empID)
    {
        int curPage=1;
        empRepo.deleteById(empID);
        Pageable pageable = (Pageable) PageRequest.of(curPage-1,maxSize, Sort.by("id").descending());
        Page<Employee> page = empRepo.findAll(pageable);
        int totalPages=page.getTotalPages();
        List<Employee> emplist= page.toList();

        model.addAttribute("emplist",emplist);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("curPage",curPage);

        model.addAttribute("emplist",emplist);
        model.addAttribute("msg","Employee deleted successfully");
        model.addAttribute("msg", "Record deleted successfully");

        return "EmpReg";
    }
    int maxSize=5;
    @GetMapping("/emp/reg/{curPage}/")
    public String showemp(Model model , @PathVariable int curPage) {
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<Employee> page = empRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<Employee> emplist = page.toList();

        model.addAttribute("emplist", emplist);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        return "EmpReg";
    }


}







