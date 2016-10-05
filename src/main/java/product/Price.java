package product;

public class Price
{
  private String price;

  public Price(String price)
  {
    this.price = price;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Price price = (Price) o;

    return this.price != null ? this.price.equals(price.price) : price.price == null;

  }

  @Override public int hashCode()
  {
    return price != null ? price.hashCode() : 0;
  }

  @Override public String toString()
  {
    return "Price{" +
        "price='" + price + '\'' +
        '}';
  }
}
