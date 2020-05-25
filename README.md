# react-native-cincocrypt

## Getting started

`$ npm install react-native-cincocrypt --save`

### Mostly automatic installation

`$ react-native link react-native-cincocrypt`

## Usage
```javascript
import Cincocrypt from 'react-native-cincocrypt';

// TODO: What to do with the module?

CincoCrypt.encrypt({
            plaintext: 'input here',
            keyword: 'your key'
        },(response)=>{
        	//do here
   		}
});

 CincoCrypt.decrypt({
            ciphertext: 'cipherText',
            keyword: 'supposed key'
            },(response)=>{
                //do here
            });


```



