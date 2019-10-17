package pl.bs.roomBooker.models;

import javax.persistence.*;

@Entity
public class Company {

    @Id
    @SequenceGenerator(name = "myCompanySeqGen", sequenceName = "myCompanySeq", initialValue = 0, allocationSize = 100)
    @GeneratedValue(generator = "myCompanySeqGen")
    @Column(name = "companyId")
    private Long companyId;
    @Column(unique=true)
    private String name;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }
}
