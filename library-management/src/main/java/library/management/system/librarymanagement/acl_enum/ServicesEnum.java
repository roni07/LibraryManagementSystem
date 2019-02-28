package library.management.system.librarymanagement.acl_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum ServicesEnum {

    BOOK_ADMIN(new ArrayList<>(Arrays.asList(
            Operations.CREATE_BOOK, Operations.READ_BOOK,
            Operations.UPDATE_BOOK, Operations.DELETE_BOOK))),

    ISSUE_ADMIN(new ArrayList<>(Arrays.asList(
            Operations.CREATE_ISSUE, Operations.READ_ISSUE,
            Operations.UPDATE_ISSUE, Operations.DELETE_ISSUE))),

    CATEGORY_ADMIN(new ArrayList<>(Arrays.asList(
            Operations.CREATE_CATEGORY, Operations.READ_CATEGORY,
            Operations.UPDATE_CATEGORY, Operations.DELETE_CATEGORY))),

    USER_ADMIN(new ArrayList<>(Arrays.asList(
            Operations.CREATE_USER, Operations.READ_USER,
            Operations.UPDATE_USER, Operations.DELETE_USER))),

    ROLE_ADMIN(new ArrayList<>(Arrays.asList(
            Operations.CREATE_ROLE, Operations.READ_ROLE,
            Operations.UPDATE_ROLE, Operations.DELETE_ROLE))),

    ISSUE_USER(new ArrayList<>(Arrays.asList(Operations.READ_ISSUE))),

    BOOK_USER(new ArrayList<>(Arrays.asList(Operations.READ_BOOK)));

    private List<Operations> operationsList;
}
