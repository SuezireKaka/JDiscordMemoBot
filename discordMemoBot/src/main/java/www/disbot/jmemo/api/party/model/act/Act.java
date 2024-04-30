package www.disbot.jmemo.api.party.model.act;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = Shape.OBJECT)
@JsonIgnoreProperties({"range", "type"})
public enum Act {
	SM(Range.System, Type.Manage),
	
	PM(Range.Private, Type.Manage),
	
	GM(Range.Group, Type.Manage),
	GP(Range.Group, Type.Proxy),
	GA(Range.Group, Type.Announce),
	
	RC(Range.Role, Type.Create),
	RM(Range.Role, Type.Manage),
	
	UI(Range.User, Type.Invite),
	UB(Range.User, Type.Ban),
	UG(Range.User, Type.Grant),
	US(Range.User, Type.Suspend);
	
	@NonNull
	private Range range;
	@NonNull
	private Type type;
	
	private String info = range.getStr() + type.getInfo();
}
