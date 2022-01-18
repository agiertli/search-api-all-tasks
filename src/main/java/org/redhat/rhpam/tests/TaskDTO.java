package org.redhat.rhpam.tests;

public class TaskDTO {

    private Long pid;
    private Long tid;

    @Override
    public String toString() {
        return "TaskDTO{" +
                "pid=" + pid +
                ", tid=" + tid +
                ", groupId='" + groupId + '\'' +
                '}';
    }

    private String groupId;

    public TaskDTO(Long pid, Long tid, String groupId) {
        this.pid = pid;
        this.tid = tid;
        this.groupId = groupId;
    }

    public TaskDTO() {
    }


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


}
