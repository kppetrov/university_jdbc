package ua.com.foxminded.university.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.service.GroupService;

@RequestMapping("/groups")
@Controller
public class GroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    private GroupService groupService;

    @GetMapping
    public String list(Model model) {
        LOGGER.debug("Listing groups");
        List<Group> groups = groupService.getAll();
        model.addAttribute("groups", groups);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("No. of groups: {}", + groups.size());
        }
        return "groups/list";
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
}
