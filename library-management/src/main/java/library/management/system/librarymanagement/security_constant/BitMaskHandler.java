package library.management.system.librarymanagement.security_constant;

import library.management.system.librarymanagement.acl_enum.Operations;
import library.management.system.librarymanagement.acl_enum.ServicesEnum;

import java.util.ArrayList;
import java.util.List;

public class BitMaskHandler {

    public static int generateBitMaskSingleService(ServicesEnum servicesEnum){

        // let us assume that we are using 64 bits for the bitmask
        // the leftmost 16 bits are used for identifying the service
        // the rightmost 16 bits are used for identifying the operation

        int serviceMask = 0;


        serviceMask |= 1 << servicesEnum.ordinal();

        int operationMask = 0;

        for (Operations operations : servicesEnum.getOperationsList())
            operationMask |= 1 << operations.ordinal();


        return ((serviceMask << 16) | operationMask);
    }

    public static List<Integer> generateBitMask(List<ServicesEnum> servicesEnumList){

        List<Integer> bitMaskList = new ArrayList<>();

        for(ServicesEnum servicesEnum : servicesEnumList) {

            int serviceMask = 0;
            int operationMask = 0;

            serviceMask |= 1 << servicesEnum.ordinal();

            for (Operations operations : servicesEnum.getOperationsList()) {
                operationMask |= 1 << operations.ordinal();
            }

            bitMaskList.add(((serviceMask << 16) | operationMask));

        }


        return bitMaskList;
    }


    public static List<Operations> parseBitMaskGetOperations(int bitMask) {

        return null;
    }
}
