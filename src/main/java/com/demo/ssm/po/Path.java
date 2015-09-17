package com.demo.ssm.po;

/**
 * Created by gmy on 15/9/17.
 */
public class Path {
    int id;
    int start;
    int end;
    String nodes;

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", nodes='" + nodes + '\'' +
                '}';
    }

    public Path(int start, int end, String nodes) {
        this.start = start;
        this.end = end;
        this.nodes = nodes;
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

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }
}
