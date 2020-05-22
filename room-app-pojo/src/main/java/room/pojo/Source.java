package room.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Source {
    /**
     * 客源id
     */
    @Id
    @Column(name = "source_id")
    private Integer sourceId;

    /**
     * 客源名称
     */
    @Column(name = "source_name")
    private String sourceName;

    /**
     * 获取客源id
     *
     * @return source_id - 客源id
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * 设置客源id
     *
     * @param sourceId 客源id
     */
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获取客源名称
     *
     * @return source_name - 客源名称
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * 设置客源名称
     *
     * @param sourceName 客源名称
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}