import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class SociatyBot extends TelegramLongPollingBot {

    /*private InlineKeyboardMarkup setInline() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new InlineKeyboardButton().setText("ЦРБ").setCallbackData("А"));

        buttons1.add(new InlineKeyboardButton().setText("ГКБ №7").setCallbackData("А"));

        buttons1.add(new InlineKeyboardButton().setText("ГКБ №3").setCallbackData("А"));

        buttons1.add(new InlineKeyboardButton().setText("ДБ №3").setCallbackData("А"));

        buttons1.add(new InlineKeyboardButton().setText("ДБ №6").setCallbackData("А"));


         buttons.add(buttons1);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        return markupKeyboard;
    } */
    /*   public synchronized ReplyKeyboardMarkup setButtons () {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Октябрьский"));
        keyboardFirstRow.add (new KeyboardButton("Ленинскиий"));
        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Фрунзенский"));
        keyboardSecondRow.add (new KeyboardButton("Советский"));
        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    } */

    public synchronized SendMessage sendMsg(Long chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String path = "C:\\Users\\sasas\\Desktop\\File.txt";
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();

        if (update.hasMessage()) {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Здравствуйте, пожалуйста введите информацию в заданной последовательности:" +
                    "Посещенное мед. учреждение, Дата посещения, лечащий врач, ваш отзыв");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getMessage();
        }
        try {
            Files.writeString(Paths.get(path), message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return "Sociaty_initiatives_bot";
    }

    @Override
    public String getBotToken() {
        return "2023633151:AAG26anA9R-hyPzk80qbQEqdoWhKPhXmMbM";
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new SociatyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
