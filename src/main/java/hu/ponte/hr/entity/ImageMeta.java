package hu.ponte.hr.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author zoltan
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageMeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "mimeType", nullable = false)
	private String mimeType;

	@Column(name = "size", nullable = false)
	private long size;

	@Column(name = "digitalSign", nullable = false)
	private String digitalSign;
}
