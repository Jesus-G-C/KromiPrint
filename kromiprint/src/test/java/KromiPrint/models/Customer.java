package KromiPrint.models;
import java.util.Objects;

public class Customer {
private String name;
private String phone; // optional for NOW
private String address; // optional for NOW


public Customer(String name, String phone, String address) {
this.name = name;
this.phone = phone;
this.address = address;
}


public String getName() { return name; }
public String getPhone() { return phone; }
public String getAddress() { return address; }


@Override
public String toString() {
return "Customer{" +
"name='" + name + '\'' +
", phone='" + (phone == null ? "-" : phone) + '\'' +
", address='" + (address == null ? "-" : address) + '\'' +
'}';
}


@Override
public boolean equals(Object o) {
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
Customer customer = (Customer) o;
return Objects.equals(name, customer.name) &&
Objects.equals(phone, customer.phone) &&
Objects.equals(address, customer.address);
}


@Override
public int hashCode() { return Objects.hash(name, phone, address); }
}