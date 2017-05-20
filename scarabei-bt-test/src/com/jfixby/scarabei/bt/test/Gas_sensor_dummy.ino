// http://www.instructables.com/id/AT-command-mode-of-HC-05-Bluetooth-module//


#include <SoftwareSerial.h>
const byte txPin = 15;
const byte rxPin = 16;
SoftwareSerial btSerial (rxPin, txPin);

const byte dataLength = 64;
byte Data[dataLength];
long GlobalCounter = 0;
int chksm = 0;
byte cnt = 0;

void setup() {
  // put your setup code here, to run once:
  pinMode(rxPin, INPUT);
  pinMode(txPin, OUTPUT);
  btSerial.begin(9600);
  Serial.begin(9600);
}


/*  Use this loop for configuring BT via USB serial terminal*/
// void loop() {ConfigBluetooth();}
 
void loop()
{
  GlobalCounter++;
  // Fill the array with noise
  Data[0] = 8;
  chksm = Data[0];
  
  for (cnt = 2; cnt<dataLength; cnt++)
  {
    Data[cnt] = random(256);
    chksm += Data[cnt];
  }
  // put in the checksum
  Data[1] = (byte) (chksm&0xFF);


  // send the report
  btSerial.print("~Message№");
  btSerial.print(GlobalCounter);
  btSerial.print("~FromABCboard");
  btSerial.print("~#####>");
  btSerial.write(Data, dataLength);
  btSerial.print("<##\n");

  // add some delay ~2 sec
  for (cnt = 0; cnt<random(256); cnt++)
    delay(100);
}























  







void ConfigBluetooth()
{
  //read from the HM-10 and print in the Serial
  if(btSerial.available())
    Serial.write(btSerial.read());
    
  //read from the Serial and print to the HM-10
  if(Serial.available())
    btSerial.write(Serial.read());
/*
Command
Description
Options
Response
AT+VERSION
Returns the software version of the module

OKlinvorV1.x
AT+BAUDx
Sets the baud rate of the module 
The command AT+BAUD8 sets the 
baud rate to 115200
1 >> 1200 
2 >> 2400 
3 >> 4800 
4 >> 9600 (Default) 
5 >> 19200 
6 >> 38400 
7 >> 57600 
8 >> 115200 
9 >> 230400
OK115200
AT+NAMEOpenPilot
Sets the name of the module
Any name can be specified up to 20 characters
OKsetname
AT+PINxxxx
Sets the pairing password of the device
Any 4 digit number can be used, the default 
pincode is 1234
OKsetPIN
AT+PN
Sets the parity of the module
AT+PN >> No parity check
OK None
*/
  
}   
