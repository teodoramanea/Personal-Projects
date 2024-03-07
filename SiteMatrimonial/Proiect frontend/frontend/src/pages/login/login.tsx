import { Button, TextField } from "@mui/material"
import {loginButtonStyle, parentDivStyle, signUpButton} from "./login.styles"
import React from "react";

import { useNavigate } from "react-router-dom";
import axios from "axios";


export const Login = (): JSX.Element => {
    const [email, setEmail] = React.useState<string>("");
    const [password, setPassword] = React.useState<string>("");
    const [loginError, setLoginError] = React.useState<string>("");
    const navigate = useNavigate();

    const onChangeEmail = (event: any): void => {
        setEmail(event.target.value)
    }

    const onChangePassword = (event: any): void => {
        setPassword(event.target.value)
    }

    const Login = (event: any): void => {

        axios.get("http://localhost:8081/User/Login",{
            params: {
                emailAddress: email,
                password: password
            }

        })
            .then(response => {
                console.log(password);
                if(password.length == 0 || password != response.data.password) {
                    setLoginError("wrong password");
                    alert("wrong password");
                   // return 0;
                }
                else{
                    console.log(response.data.admin);
                    if(response.data.admin)
                    {
                        navigate("/Admin");
                    }
                    else{
                        localStorage.setItem("user", JSON.stringify(response.data));
                        console.log(response.data);
                        navigate("/Profile");
                    }

                }

            })
            .catch(error => {
                console.error("Login failed", error);
            });

    };

    const SignUp = (event: any): void =>
    {
        navigate("/signUp");
    }


    return <div>

          <div
                style={{
                    width: '100%',
                    height: '100%',
                    position: 'fixed',
                    left: 0,
                    top: 0,
                    backgroundColor: "#ABB88D"
                }}>
        </div>

        <div>
            <img
                style={{
                    width: '564px',
                    height: '800px',
                    position: 'fixed',
                    left: 400,
                    top: 0,
                    borderRadius: '30px',
                    opacity: 0.9,
                    //mixBlendMode: 'overlay'

                }}

                src={require('./../../poze/poza_draguta.jpg')}
                alt = "poza de login"
            />

        </div>

        <h1 style={{
            top : 100, left:625,  position: "relative", color:'white',  fontFamily: ' Montserrat', fontWeight: 50}}>Login</h1>
        <div style = {{ position: "relative", top : 100, left:570}}>
            <TextField id="email" label="Email" variant="standard" onChange={onChangeEmail}
                       InputLabelProps={{
                           style: { color: "white" ,  fontFamily: ' Montserrat'},
                       }}
                       InputProps={{
                           style: {
                               color: "white",
                               background: "transparent",
                               border: "1px solid white",
                               borderRadius: "5px",
                               fontFamily: ' Montserrat'
                           },
                       }}
            />
        </div>
        <div style = {{ position: "relative", top : 120, left:570}}>
            <TextField id="password" type= "password" label="Password" variant="standard" onChange={onChangePassword}
                       InputLabelProps={{
                           style: { color: "white",  fontFamily: ' Montserrat' },
                       }}
                       InputProps={{
                           style: {
                               color: "white",
                               background: "transparent",
                               border: "1px solid white",
                               borderRadius: "5px",
                               fontFamily: ' Montserrat'
                           },
                       }}
            />

        </div>
        <Button style={loginButtonStyle} onClick={Login} variant="contained">Login</Button>
        <Button style={signUpButton} onClick={SignUp} variant = "contained">Sign Up to find your soulmate</Button>
        {loginError ? <p style={{ color: 'red' }}>{loginError}</p> : null}

    </div>
}