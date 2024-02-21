/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.EstacionPodocarpus;

import Controller.TDAListas.LinkedList;
import com.thoughtworks.xstream.XStream;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 *
 * @author Usuario
 */
public class DataAccessObject<T> implements TransferObject<T> {

    private XStream xstream;
    private Class<T> clazz;
    private String URL;

    public DataAccessObject(Class<T> clazz) {
        xstream = Conection.getXstream();
        this.clazz = clazz;
        URL = Conection.getURL() + this.clazz.getSimpleName() + ".json";
    }

    @Override
    public Boolean save(T data) {
        LinkedList<T> list = listAll();
        list.add(data);
        try {
            this.getXstream().toXML(list, new FileOutputStream(URL));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Boolean update(T data, Integer index) {
        LinkedList<T> list = listAll();

        if (index >= 0 && index < list.getSize()) {
            try {
                {
                    list.add(data, index);
                    this.getXstream().toXML(list, new FileOutputStream(URL));
                    return true;
                }

            } catch (Exception e) {
                System.out.println("No se puede guardar debido a la mala infor");
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public LinkedList<T> listAll() {
        LinkedList<T> list = new LinkedList<>();
        try {
            list = (LinkedList<T>) getXstream().fromXML(new FileReader(URL));
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public T find(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer generarId() {
        return listAll().getSize() + 1;

    }

    /**
     * @return the xstream
     */
    public XStream getXstream() {
        return xstream;
    }

    
    
}
