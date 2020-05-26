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
            keyword: 'your key',
            startAt: '32',
            endAt: '126'
        },(response)=>{
        	//do here
   		}
});

 CincoCrypt.decrypt({
            ciphertext: 'cipherText',
            keyword: 'supposed key',
            startAt: '32',
            endAt: '126'
            },(response)=>{
                //do here
            });


```



