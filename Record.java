/* Itong class na 'to para  sa isang record.
 */

public class Record {
    private String name; // Yung name ng tao
    private int idNumber; // Yung ID number ng tao

    public Record(String name, int idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    /* Getters lang 'to */
    public String getName() {
        return name;
    }

    public int getIdNumber() {
        return idNumber;
    }

   
}
