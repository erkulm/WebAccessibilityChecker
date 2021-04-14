package edu.itu.wac.controller.rest;

import edu.itu.wac.service.PermissionService;
import edu.itu.wac.service.request.PermissionRequest;
import edu.itu.wac.service.response.PermissionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionRestController {
    private final PermissionService permissionService;

    @Autowired
    public PermissionRestController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("insert")
    public ResponseEntity<PermissionResponse> insert(@RequestBody PermissionRequest permissionRequest) {

        if (!StringUtils.isEmpty(permissionRequest.getName())) {
            PermissionResponse permissionResponse = permissionService.save(permissionRequest);
            return new ResponseEntity<>(permissionResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<PermissionResponse> get(@PathVariable String id) {
        PermissionResponse permissionResponse = permissionService.getById(id);
        if (permissionResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(permissionResponse, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<PermissionResponse>> getAll() {
        List<PermissionResponse> permissionResponses = permissionService.getAll();
        return new ResponseEntity<>(permissionResponses, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<PermissionResponse>
    update(@Valid @RequestBody PermissionRequest permissionRequest) {

        PermissionResponse permissionResponse = permissionService.getById(permissionRequest.getId());
        if (permissionResponse==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!StringUtils.isEmpty(permissionRequest.getName())) {

            PermissionResponse newPermissionResponse = permissionService.save(permissionRequest);
            return new ResponseEntity<>(newPermissionResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        PermissionResponse permission = permissionService.getById(id);

        if (permission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        permissionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
