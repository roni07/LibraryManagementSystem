package library.management.system.librarymanagement;

import library.management.system.librarymanagement.acl_enum.Operations;
import library.management.system.librarymanagement.acl_enum.ServicesEnum;
import library.management.system.librarymanagement.security_constant.BitMaskHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryManagementApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void bitmask() {


        System.out.println("book admin = "+Integer.toBinaryString(
                BitMaskHandler.generateBitMaskSingleService
                        (ServicesEnum.BOOK_ADMIN)));
        System.out.println("Issue admin = "+Integer.toBinaryString
                (BitMaskHandler.generateBitMaskSingleService
                (ServicesEnum.ISSUE_ADMIN)));
        System.out.println("====================================");

        int a = ServicesEnum.BOOK_ADMIN.ordinal();
        int b = ServicesEnum.ISSUE_USER.ordinal();
        int d = ServicesEnum.ISSUE_ADMIN.ordinal();
        int c = ServicesEnum.BOOK_USER.ordinal();

        List<Integer> integers = BitMaskHandler.generateBitMask(
                new ArrayList<>(Arrays.asList(ServicesEnum.BOOK_ADMIN,
                        ServicesEnum.ISSUE_ADMIN)));

        for (Integer integer : integers) {
            System.out.println(Integer.toBinaryString(integer));
        }

        // Check koren ........hmm

        System.out.println("===================================");

        System.out.println("test a = "+a);
        System.out.println("test b = "+b);
        System.out.println("test c = "+c);
        System.out.println("test d = "+d);
    }
}