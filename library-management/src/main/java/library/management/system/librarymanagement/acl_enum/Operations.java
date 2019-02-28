package library.management.system.librarymanagement.acl_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Operations {
    CREATE_BOOK("Create_Book"),
    READ_BOOK("Read_Book"),
    UPDATE_BOOK("Update_Book"),
    DELETE_BOOK("Delete_Book"),

    CREATE_ISSUE("create_issue"),
    READ_ISSUE("read_issue"),
    UPDATE_ISSUE("update_issue"),
    DELETE_ISSUE("delete_issue"),

    CREATE_CATEGORY("create_category"),
    READ_CATEGORY("read_category"),
    UPDATE_CATEGORY("update_category"),
    DELETE_CATEGORY("delete_category"),

    CREATE_ROLE("create_role"),
    READ_ROLE("read_role"),
    UPDATE_ROLE("update_role"),
    DELETE_ROLE("delete_role"),

    CREATE_USER("create_role"),
    READ_USER("read_role"),
    UPDATE_USER("update_role"),
    DELETE_USER("delete_role");

    private String name;
}
