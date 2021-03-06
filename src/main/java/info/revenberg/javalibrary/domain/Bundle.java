package info.revenberg.javalibrary.domain;

import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import info.revenberg.javalibrary.domain.AuditModel;

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table(name = "bundle")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bundle extends AuditModel {    
    private static final long serialVersionUID = -4206755717683730837L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @Column(nullable = false)
    private long bundleid;

    @Column(nullable = false)
    private String name;

    @Column()
    private String mnemonic;

    @OneToMany(mappedBy = "bundle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Song> songs;

    public Bundle() {
    }

    public Bundle(String name) {
        setName(name);
    }

    public Bundle(long bundleid, String name, String mnemonic) {
        setBundleid(bundleid);
        setName(name);
        setMnemonic(mnemonic);
    }

    public void addSong(Song song) {
        songs.add(song);
        song.setBundle(this);
    }

    public void removeSong(Song song) {
        songs.remove(song);
        song.setBundle(null);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public long getBundleid() {
        return bundleid;
    }

    public void setBundleid(long bundleid) {
        this.bundleid = bundleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }
}
