package views;

/**
 * This is kind of scuffed but it works for now
 */
public final class Constants {

  public static final String[] dropdownOptions = {
    "Get Product By ID",
    "Get Products By Product Type",
  };

  public static final class Table {

    public static final String[] productCols = {
      "ProductId",
      "ProductName",
      "ProductQuantity",
      "ProductPrice",
      "ProductDescription",
      "TypeId",
    };
    public static final String[] defaultColumns = productCols;
    public static final Object[][] emptyTable = new Object[0][productCols.length];
  }

  public static String[] getColumns(int index) {
    switch (index) {
      case 0:
        return Table.productCols;
      case 1:
        return Table.productCols;
      default:
        return Table.defaultColumns;
    }
  }
}