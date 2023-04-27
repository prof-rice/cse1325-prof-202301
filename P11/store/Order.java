package store;

import java.util.ArrayList;
import java.util.Objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Order implements Saveable {
    public Order(Customer customer) {
        this.customer = customer;
        this.orderNumber = nextOrderNumber++;
    }
    public Order(BufferedReader br) throws IOException {
        this.orderNumber = Long.parseLong(br.readLine());
        if(nextOrderNumber <= orderNumber) nextOrderNumber = orderNumber + 1;
        this.customer = new Customer(br);
        int numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            computers.add(new Computer(br));
    }
    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(Long.toString(orderNumber) + '\n');
        this.customer.save(bw);
        bw.write("" + computers.size() + '\n');
        for(Computer computer : computers)
            computer.save(bw);
    }
    
    public void addComputer(Computer computer) {
        computers.add(computer);
    }
    public long cost() {
        long cost = 0;
        for(Computer c : computers) cost += c.cost();
        return cost;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order " + orderNumber 
            + " for " + customer + " at $" + cost()/100.0);
        for(Computer c : computers) sb.append("\n\n" + c);
        return sb.toString();
    }

    // WARNING: The equals method relies on a stable toString method
    //   that includes ALL significant fields to be compared
    // This includes the order in which Computer objects were added
    // A better implementation would sort field computers,
    //   which would require that Option implement Comparable
    // We'll leave that as a challenge for the enthusiastic student
    @Override
    public boolean equals(Object o) {
        try {
            if(this == o) return true;
            if(this.getClass() != o.getClass()) return false;
            Order c = (Order) o;
            if(!this.customer.equals(c.customer)) return false;
            if(this.computers.size() != c.computers.size()) return false;
            for(int i=0; i<this.computers.size(); ++i)
                if(!this.computers.get(i).equals(c.computers.get(i))) return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(customer, computers);
    }

    private Customer customer;
    private long orderNumber;
    private ArrayList<Computer> computers = new ArrayList<>();
    
    private static long nextOrderNumber = 0;
}
