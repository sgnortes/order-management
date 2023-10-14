package com.sgnortes.ordermanagement.common.constants;

/**
 * Class to define Swagger constants
 * @author Sergio
 */
public class SwaggerConstants {
    public static final String DESCRIPTION_PAGINATED_ENDPOINT =
            "This endpoint uses speficications, pagination and sorting. " +
            "\n ## How specifications work:" +
            "\n The principal filters supported are the following:" +
            "\n" +
            "| **Filter** | **Description**            |\n" +
            "| ---------- | -------------------------- |\n" +
            "| **eq:**    | equivalent to equal        |\n" +
            "| **neq:**   | equivalente to not equal   |\n" +
            "| **gt:**    | equivalent to greater than |\n" +
            "| **lt:**    | equivalent to less than    |\n" +
            "| **like:**  | equivalent to like         |\n" +
            "| **in:**    | equivalent to in           |\n" +
            "\n" +
            "To use them you have to include them in any of the fields you want to filter.\n" +
            "\n" +
            "The format you must use is the following one => 'filter':'valueToFilter'.\n" +
            "\n" +
            "For example, to filter by the field 'id' equal to 1, you should write the following => 'eq:1'.\n" +
            "\n" +
            "If you want to use the 'in' clause to filter, you will need to pass a list of values separated by commas. For example => 'in:1,2,3,4'." +
            "\n ## How pagination works:" +
            "\n To use pagination you have to enter the value of the page you want to search and the amount of results you want to read. " +
            "\n To do so, you would assign parameter 'page' the value of 0 and parameter 'size' the value of 10." +
            "\n ## How sorting works:" +
            "\n To sort data you should populate the field 'sort' with the sorting criteria you want to apply." +
            "\n 'Sort' field is an array of strings that has the following format: 'fieldName,order', where order can be 'asc' for ascending and 'desc' for descending. " +
            "\n If you want, you can even include multiple order criteria, by including more registers into the array."
            ;
}
