/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package mikegpl.sr.thrift;


public enum Currency implements org.apache.thrift.TEnum {
  PLN(0),
  USD(1),
  EUR(2),
  VEF(3);

  private final int value;

  private Currency(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static Currency findByValue(int value) { 
    switch (value) {
      case 0:
        return PLN;
      case 1:
        return USD;
      case 2:
        return EUR;
      case 3:
        return VEF;
      default:
        return null;
    }
  }
}
