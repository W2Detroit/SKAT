#include <Wire.h> 
#include <LiquidCrystal_I2C.h>

#include <Adafruit_MLX90614.h>

Adafruit_MLX90614 mlx;
LiquidCrystal_I2C lcd(0x27,16,2);

void setup ()
{
   mlx.begin();
   lcd.init();
}

void loop()
{
   lcd.clear();
   lcd.backlight();
   lcd.setCursor(0,1);
   lcd.print("SKAT 2021");
   float temp{};
   while(true)
   {
    temp= mlx.readObjectTempC();
       lcd.setCursor(0,0);
       lcd.print(temp);
       if(temp>33)delay(500);
    delay(500);
   }
}
