import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CodeExamplesAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);

        //get full class name
        String selectedClassName = editor.getSelectionModel().getSelectedText();

        //It work
        //Messages.showMessageDialog("Ok!","Code Examples", Messages.getInformationIcon());

        //get URL
        if (selectedClassName != null){
            String encoded = "";

            try {
                encoded = URLEncoder.encode(selectedClassName, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
            }

            String url = String.format("https://www.programcreek.com/java-api-examples/?action=search&ClassName=%s&submit=Search",encoded);
            //Open site
            BrowserUtil.browse(url);
        }
        else{
            Messages.showMessageDialog("Selected area is empty! Could you please to select something?","Code Examples", Messages.getErrorIcon());
        }

        //
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}

