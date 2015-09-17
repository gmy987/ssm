package com.demo.ssm.po;

/**
 * Created by gmy on 15/9/17.
 */
public class Edge {
    private int id;
    private int start;
    private int end;
    private String name;

    @Override
    public String toString() {
        return "Edge{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", name='" + name + '\'' +
                '}';
    }

    public Edge(int start, int end, String name) {
//        this.id = id;
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
