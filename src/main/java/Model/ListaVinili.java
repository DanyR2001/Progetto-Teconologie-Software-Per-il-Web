package Model;

import java.util.ArrayList;
import java.util.List;

public class ListaVinili {
    ArrayList<Vinile> list;
    ArrayList<Integer> size;

    public ListaVinili(){
        list = new ArrayList<>();
        size= new ArrayList<>();
    }

    /*public void aggiorna(vinile v, int quatita){
        for(vinile v1: list)
            if(v1.equals(v))
                if(v1.getQuantita()>=quatita)
                    v1.removeNumItem(quatita);
    }*/

    /*public void setDisponibili(vinile v,int quntita){
        for(vinile v1: list)
            if(v1.equals(v))
                v1.setQuantita(quntita);
    }*/

    public Vinile findViniliFromId(int id){
        if(list.size()>0)
            for(Vinile v: list)
                if(v.getPK()==id)
                    return v;
        return null;
    }

    public ArrayList<Vinile> getAllVinili(){
        if(list.size()>0)
            return list;
        return null;
    }

    public ListaVinili getAvailableVinili(){
        ListaVinili ret=null;
        if(list.size()>0){
            ret=new ListaVinili();
            for(int i=0;i<list.size();i++)
                if(size.get(i)>0) {
                    ret.add(list.get(i),size.get(i));
                }
        }
        return ret;
    }

    public ListaVinili getFromTag(Tag Tag){
        ListaVinili ret=null;
        if(Tag!=null) {
            ret = new ListaVinili();
            for (int i = 0; i < list.size(); i++) {
                Vinile v = list.get(i);
                if (v.getTags() != null) {
                    for (int j = 0; j < v.getTags().size(); j++)
                        if (v.getTags().get(j).equals(Tag))
                            ret.add(v,size.get(i));
                }
            }
            return ret;
        }
        return null;
    }

    public void add(Vinile v, Integer disponibilita){
        list.add(v);
        size.add(disponibilita);
    }

    public boolean isAvable(Vinile v){
        if(list.size()>0)
            for (int i=0;i<list.size();i++) {
                System.out.println("ciao 2");
                if (list.get(i).equals(v)) {
                    System.out.println("ciao 1");
                    if (size.get(i) > 0) {
                        System.out.println("ciao");
                        return true;
                    }
                    else
                        return false;
                }
            }
        return false;
    }

    public Integer getQuantitaVin(Vinile v){
        if(list.size()>0)
            for (int i=0;i<list.size();i++)
                if(list.get(i).equals(v))
                    return size.get(i);
        return -1;
    }

    public void setQuantitaVin(Vinile v, Integer quantita){
        if(list.size()>0)
            for (int i=0;i<list.size();i++)
                if(list.get(i).equals(v)){
                    size.remove(i);
                    size.add(i,quantita);
                }
    }



    /*public int numDispVinil(vinile v){
        int size=0;
        if(list.size()>0)
            for(vinile a: list)
                if(v.equals(a))
                    size=a.getQuantita();
        return size;
    }*/
    public Vinile get(int index){
        return list.get(index);
    }
    public int size(){
        return list.size();
    }
    public Integer getMaxDisp(int index){
        return size.get(index);
    }

    public Integer getMaxDispId(int id){
        for(int i=0;i<list.size();i++)
            if(list.get(i).getPK() == id)
                return size.get(i);
        return 0;
    }

    public List<Vinile> getTitleContain(String name){
        List<Vinile> ret=new ArrayList<>();
        for(int i=0;i<list.size()&&name.length()>0;i++){
            Vinile v=list.get(i);
            if(v.getTitolo().toLowerCase().contains(name.toLowerCase())){
                if(isAvable(v))
                    ret.add(v);
            }
        }
        if(ret.size()==0)
            return null;
        return ret;
    }

    public List<Vinile> getListFromTag(String[] tags){
        List<Vinile> ret=new ArrayList();
        for(Vinile v: list) {
            boolean flag=false;
            for (int i=0;i<tags.length&&flag==false;i++) {
                flag = true;
                if(v.getTags()!=null) {
                    for (int j = 0; j < v.getTags().size(); j++) {
                        Tag x = v.getTags().get(j);
                        if (x.getNome().equals(tags[i]))
                            flag = false;
                    }
                }
            }
            if(flag==false&&isAvable(v))
                ret.add(v);
        }
        if(ret.size()>0)
            return ret;
        return null;
    }





}