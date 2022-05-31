package ch.bbw.pg.message;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * A chat message
 * 
 * @author Peter Rutschmann
 * @version 25.03.2020
 */
@Entity
@Table(name = "message")
@Getter
@Setter
@ToString
public class Message {
	@Id
    @GeneratedValue(generator = "generatorMessage", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generatorMessage", initialValue=10)
	private Long id;
	
	@NotEmpty (message = "content may not be empty" )
	@Size(min=2, max=512, message="message: Length 2 - 512 required")
	private String content;
	
	//@NotEmpty (message = "author may not be empty" )
	@Size(min=5, max=20, message="author: Length 1 - 51 required")
	private String author;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date origin ;

}
