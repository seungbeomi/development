package seungbeomi.web.springmvc.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PropertyEditorTest {

	@Test
	public void testPropertyEditor() {
		LevelPropertyEditor editor = new LevelPropertyEditor();

		editor.setAsText("3");
		assertThat((Level)editor.getValue(), is(Level.GOLD));

		editor.setValue(Level.SILVER);
		assertThat(editor.getAsText(), is("2"));
	}

}
