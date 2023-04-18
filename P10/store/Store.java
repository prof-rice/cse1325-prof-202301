package store;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Store {
    public Store(String name) {
        this.name = name;
    }
     public Store(BufferedReader br) throws IOException {
        this.name = br.readLine();
        int numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            customers.add(new Customer(br));

        numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            options.add(new Option(br));

        numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            computers.add(new Computer(br));

        numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            orders.add(new Order(br));
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        
        bw.write("" + customers.size() + '\n');
        for(Customer customer : customers)
            customer.save(bw);

        bw.write("" + options.size() + '\n');
        for(Option option : options)
            option.save(bw);

        bw.write("" + computers.size() + '\n');
        for(Computer computer : computers)
            computer.save(bw);

        bw.write("" + orders.size() + '\n');
        for(Order order : orders)
            order.save(bw);

    }
   public String name() {
        return this.name;
    }
    
    // ///////////////////////////////////////////////////////////
    // Customers
    
    public void add(Customer customer) {
        if(!customers.contains(customer)) customers.add(customer);
    }
    public Object[] customers() {
        return this.customers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Options
    
    public void add(Option option) {
        if(!options.contains(option)) options.add(option);
    }
    public Object[] options() {
        return this.options.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Computers
    
    public void add(Computer computer) {
        if(!computers.contains(computer)) computers.add(computer);
    }
    public Object[] computers() {
        return this.computers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Orders
    
    public void add(Order order) {
        if(!orders.contains(order)) orders.add(order);
    }
    public Object[] orders() {
        return this.orders.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Fields
    
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Option> options = new ArrayList<>();
    private ArrayList<Computer> computers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
}
