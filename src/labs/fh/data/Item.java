/*
 * Copyright (C) 2023 bhagc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package labs.fh.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author bhagc
 */
public class Item implements Serializable{
    private static final long SERIAL_VERSION=2L;
    private Set<Product> products;
    private final LocalDateTime time_stamp;
    private transient byte[] hash=new byte[32];

    public Item() {
        products=new HashSet<>();
        time_stamp=LocalDateTime.now();
    }

    public Set<Product> getProducts() {
        return products;
    }
    
    public boolean addProduct(Product product){
       return products.add(product);
    }
   
    
    private void writeObject(ObjectOutputStream out)throws IOException{
        out.defaultWriteObject();
        out.writeObject(this);
    }
    
    private void readObject(ObjectInputStream in)throws IOException,ClassNotFoundException, NoSuchAlgorithmException{
        in.defaultReadObject();
        hash=generateHash(this);
        System.out.println(Arrays.toString(hash));
    }
    
    private byte[] generateHash(Object obj) throws IOException, NoSuchAlgorithmException {
       
        try(ByteArrayOutputStream by=new ByteArrayOutputStream();
                ObjectOutputStream out=new ObjectOutputStream(by)){
            out.writeObject(obj);
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            hash=md.digest(by.toByteArray());
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Item{" + "products=" + products + ", time_stamp=" + time_stamp + '}';
    }
    
    
    
}
