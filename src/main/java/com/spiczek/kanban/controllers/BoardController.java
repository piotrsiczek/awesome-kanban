package com.spiczek.kanban.controllers;

import com.spiczek.kanban.apis.BoardApi;
import com.spiczek.kanban.collections.Board;
import com.spiczek.kanban.collections.Group;
import com.spiczek.kanban.collections.Item;
import com.spiczek.kanban.config.AuthUser;
import com.spiczek.kanban.model.GroupResult;
import com.spiczek.kanban.model.data.BoardData;
import com.spiczek.kanban.model.data.GroupData;
import com.spiczek.kanban.model.data.ItemData;
import com.spiczek.kanban.model.data.ItemStatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Piotr Siczek
 */
@RestController
@RequestMapping("/api")
public class BoardController {

    private BoardApi api;

	public String getAuthenticatedUserId() {
		return ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
	}

    @Autowired
    public BoardController(BoardApi api) {
        this.api = api;
    }

    @RequestMapping("/board")
    public ResponseEntity<List<Group>> getBoard(@RequestParam(name = "groupId") List<String> groupIds) {
	    String id = getAuthenticatedUserId();
	    List<Group> groups = api.getBoard(groupIds);

	    if(!groups.stream().anyMatch(g -> g.getAcl().getCreator().equals(id) || g.getAcl().getR().equals(id))) {
		    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	    }

	    return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public Board createBoard(@RequestBody BoardData data) {
        return api.createBoard(data.getOwnerId(), data.getTitle());
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public Group createGroup(@RequestBody GroupData data) {
        return api.createGroup(data.getTitle(), data.getBoardId(), getAuthenticatedUserId());
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public Item createItem(@RequestBody ItemData data) {
        return api.createItem(data.getText(), data.getGroupId());
    }

    @RequestMapping(value = "/item/status", method = RequestMethod.PUT)
    public void changeItemStatus(@RequestBody ItemStatusData data) {
        api.changeItemStatus(data.getGroupIdFrom(), data.getGroupIdTo(), data.getItemId());
    }
}
