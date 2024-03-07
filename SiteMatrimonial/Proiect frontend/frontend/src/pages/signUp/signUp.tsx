import axios, {AxiosError} from "axios";
import {parentDivStyle, registrateButton, registrationTag} from "./signUp.styles";
import React from "react";
import { Button, TextField } from "@mui/material"



export const SignUp = (): JSX.Element =>
{
    const [name, setName] = React.useState<string>("");
    const [emailAddress, setEmailAddress] = React.useState<string>("");
    const [password, setPassword] = React.useState<string>("");
    const [address, setAddress] = React.useState<string>("");
    const [telNumber, setTelNumber] = React.useState<string>("")
    const [repeatPassword, setRepeatPassword] = React.useState<string>("");
    const admin = false;
    const disableQuiz = false;

    ///pentru afisare erori
    const [nameError, setNameError] = React.useState<string>("");
    const [emailError, setEmailError] = React.useState<string>("");
    const [passwordError, setPasswordError] = React.useState<string>("");
    const [addressError, setAddressError] = React.useState<string>("");
    const [telNumberError, setTelNumberError] = React.useState<string>("")
    const [repeatPasswordError, setRepeatPasswordError] = React.useState<string>("");


    const [registrationSuccess, setRegistrationSuccess] = React.useState(false);
    const onChangeName = (event: any): void => {
        const enteredName = event.target.value;
            setNameError('');
            setName(enteredName);
            console.log(name);

    }
    const onChangeEmail = (event: any): void => {
        const enteredEmail = event.target.value;
            setEmailError('');
            setEmailAddress(enteredEmail);
            console.log(emailAddress);
    }

    const onChangeAddress = (event: any): void => {
        const enteredAddress = event.target.value;
            setAddressError('');
            setAddress(enteredAddress)
            console.log(address);

    }

    const onChangeTelNumber = (event: any): void => {
        const enteredTelNumber = event.target.value;
            setTelNumberError('');
            setTelNumber(event.target.value)
            console.log(telNumber);

    }
    const onChangePassword = (event: any): void => {
        const enteredPassword = event.target.value;

            setPasswordError('');
            setPassword(enteredPassword)
            console.log(password);

    }

    const onChangeRepeatPassword = (event: any): void => {
        const enteredPasswordAgain = event.target.value;
            setRepeatPasswordError('');
            setRepeatPassword(enteredPasswordAgain)
            console.log(repeatPassword);
    }




    const Registrate = (event: any): void =>{
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;


        if(password == repeatPassword && name != '' && address != '' && telNumber.length == 9 && telNumber != '' && emailAddress != '' && emailRegex.test(emailAddress) && password != '' && password.length >= 8 && repeatPassword != '')
        {

            const userData = { name, admin, disableQuiz, address, telNumber, emailAddress, password };
            axios.post("http://localhost:8081/User/Insert", userData);
            setRegistrationSuccess(true);


        }else{

            if(name.trim() == '')
                setNameError("incomplete name filed!");
            if(!emailRegex.test(emailAddress) || emailAddress == '')
                setEmailError("invalid email address!");
            if(address.trim() == '')
                setAddressError("incomplete address filed");
            if(telNumber.length != 9 || telNumber == '')
                setTelNumberError("invalid phone number!");
            if(password == '')
                setPasswordError("incomplete password filed!");
            else if(password.length < 8)
                setPasswordError("the password needs to have at least 8 characters!");
            if(repeatPassword == '')
                setRepeatPasswordError("please verify your password here!");
            else if(repeatPassword != password)
                setRepeatPasswordError("passwords don't match!");
        }
    }


    return <div style={parentDivStyle}>

          <div>
            <img
                style={{
                    width: '100%',
                    height: '100%',
                    position: 'fixed',
                    left: 0,
                    top: 0,
                    borderRadius: '10px',
                    zIndex: -1,
                    filter: 'blur(3px)'
                }}

                src={require('./../../poze/sign_in.jpg')}
                alt = "poza de sign up"
            />

        </div>

        <h1 style={{
           top : 15, left : 25, position: "relative", color:'black',  fontFamily: ' Montserrat', fontWeight: 50}}>Sign Up</h1>
        <div style={{ marginTop: 20}}>
            <TextField id="name" label="Name" variant="standard" onChange={onChangeName}
                       InputLabelProps={{
                           style: { color: "black" ,  fontFamily: ' Montserrat'},
                       }}/>
             {nameError ? <p style={{ color: 'red' }}>{nameError}</p> : null}
        </div>
        <div style={{ marginTop: 20}}>
            <TextField id="address" label="address" variant="standard" onChange={onChangeAddress}
                       InputLabelProps={{
                           style: { color: "black" ,  fontFamily: ' Montserrat'},
                       }}
            />
            {addressError ? <p style={{ color: 'red' }}>{addressError}</p> : null}
        </div>
        <div style={{ marginTop: 20}}>
            <TextField id="telNumber" label="phone number" variant="standard" onChange={onChangeTelNumber}
                       InputLabelProps={{
                           style: { color: "black" ,  fontFamily: ' Montserrat'},
                       }}/>
            {telNumberError ? <p style={{ color: 'red' }}>{telNumberError}</p> : null}
        </div>
        <div style={{ marginTop: 20}}>
            <TextField id="emailAddress" label="email address" variant="standard" onChange={onChangeEmail}  InputLabelProps={{
                style: { color: "black" ,  fontFamily: ' Montserrat'},
            }}/>
            {emailError ? <p style={{ color: 'red' }}>{emailError}</p> : null}
        </div>
        <div style={{ marginTop: 20}}>
            <TextField id="password" label="password" variant="standard" onChange={onChangePassword}  InputLabelProps={{
                style: { color: "black" ,  fontFamily: ' Montserrat'},
            }}/>
            {passwordError ? <p style={{ color: 'red' }}>{passwordError}</p> : null}

        </div>
        <div style={{ marginTop: 20}}>
            <TextField id="confirmPassword" label="confirm password" variant="standard" onChange={onChangeRepeatPassword} InputLabelProps={{
                style: { color: "black" ,  fontFamily: ' Montserrat'},
            }}/>
            {repeatPasswordError ? <p style={{ color: 'red' }}>{repeatPasswordError}</p> : null}
        </div>
        <div style={{ display: 'flex', alignItems: 'center' }}>
            <Button style={registrateButton} onClick={Registrate} variant="contained">
                Registrate
            </Button>
            {registrationSuccess && (
                <p style={{...registrationTag,  color: 'black'} }>User registered successfully!</p>
            )}
        </div>
    </div>

}