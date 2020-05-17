package hu.ponte.hr.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.ponte.hr.serialize.ImageMetaSerializer;
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
@JsonSerialize(using = ImageMetaSerializer.class)
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

	@Column(name = "digitalSign", length = 344, nullable = false)
	private String digitalSign;
}
