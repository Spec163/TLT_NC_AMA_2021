package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.ComplaintDTO;
import nc.unc.ama.complaint_handling_service.dto.StaffMemberDTO;
import nc.unc.ama.complaint_handling_service.entities.Complaint;
import nc.unc.ama.complaint_handling_service.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/Complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping(path = "/createComplaint")
    public void createComplaint(@RequestBody ComplaintDTO complaintDTO){
        complaintService.createComplain(
                complaintDTO.getComplaintText(),
                complaintDTO.getGuestId(),
                complaintDTO.getStaffMemberId(),
                complaintDTO.getRoomId());
    }

    @GetMapping(path = "/viewComplaint/{complaintId}")
    public ComplaintDTO viewComplaint(@PathVariable("complaintId") Long complaintId){
        Complaint newComplaint = complaintService.getComplaint(complaintId);
        return new ComplaintDTO(
                newComplaint.getComplaintId(),
                newComplaint.getComplaintText(),
                newComplaint.getGuestId(),
                newComplaint.getStaffMemberId(),
                newComplaint.getRoomId());
    }
    @GetMapping(path = "/viewAllComplaints")
    public List<ComplaintDTO> getAllComplaints(){
        List<Complaint> complainList = new ArrayList<>(complaintService.getAllComplaints());
        List<ComplaintDTO> complainDTOList = new ArrayList<>();
        for(Complaint complaint:complainList){
            complainDTOList.add(new ComplaintDTO(
                    complaint.getComplaintId(),
                    complaint.getComplaintText(),
                    complaint.getGuestId(),
                    complaint.getStaffMemberId(),
                    complaint.getRoomId()));
        }
        return complainDTOList;
    }
    @GetMapping
    public List<ComplaintDTO> getComplaintsOnStaff(StaffMemberDTO staffMemberDTO){
        List<Complaint> complainList = new ArrayList<>(complaintService.getComplaintByStaffId(staffMemberDTO.getStaffMemberId()));
        List<ComplaintDTO> complainDTOList = new ArrayList<>();
        for(Complaint complaint:complainList){
            complainDTOList.add(new ComplaintDTO(
                    complaint.getComplaintId(),
                    complaint.getComplaintText(),
                    complaint.getGuestId(),
                    complaint.getStaffMemberId(),
                    complaint.getRoomId()));
        }
        return complainDTOList;
    }
}
