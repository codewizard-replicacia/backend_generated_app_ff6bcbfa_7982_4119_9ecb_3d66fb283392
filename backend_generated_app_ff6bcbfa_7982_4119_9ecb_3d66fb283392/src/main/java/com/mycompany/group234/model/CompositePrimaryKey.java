package com.mycompany.group234.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositePrimaryKey   implements Serializable, Comparable<CompositePrimaryKey>{

    @Id
    @Column(name = "\"Code\"", length = 10)
    private String code;

    @Id
    @Column(name = "\"CodeID\"", length = 10)
    private Integer codeID;

    @Id
    @Column(name = "\"DivisonCode\"", length = 10)
    private String divisonCode;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codeID == null) ? 0 : codeID.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((divisonCode == null) ? 0 : divisonCode.hashCode());
        return result;
    }

    @Override
    public int compareTo(final CompositePrimaryKey compositePrimaryKey) {
        Objects.requireNonNull(compositePrimaryKey);
        int result = code.compareTo(compositePrimaryKey.code);
        if (result == 0) {
            result = codeID.compareTo(compositePrimaryKey.codeID);
            if (result == 0)
                return divisonCode.compareTo(compositePrimaryKey.divisonCode);
            else
                return result;
        } else {
            return result;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CompositePrimaryKey other = (CompositePrimaryKey) obj;
        if (codeID == null) {
            if (other.codeID != null) return false;
        } else if (!codeID.equals(other.codeID)) return false;
        if (code == null) {
            if (other.code != null) return false;
        } else if (!code.equals(other.code)) return false;
        if (divisonCode == null) {
            if (other.divisonCode != null) return false;
        } else if (!divisonCode.equals(other.divisonCode)) return false;
        return true;
    }
}
