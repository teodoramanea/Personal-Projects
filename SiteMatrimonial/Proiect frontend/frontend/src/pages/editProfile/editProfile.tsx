import axios, {AxiosError} from "axios";
import React, {useEffect} from "react";
import { Button, TextField } from "@mui/material"
import Checkbox from '@mui/material/Checkbox';
import {
    deleteButtonStyle, deletionTagStyle,
    pictureStyle1,
    pictureStyle2,
    pictureStyle3,
    pictureStyle4,
    pictureStyle5,
    pictureStyle6,
    textFieldStyle, updateButtonStyle, writingStyle, writingStyle2
} from "./editProfile.styles";
import { green } from '@mui/material/colors';

import {loginButtonStyle} from "../login/login.styles";
import {registrationTag} from "../signUp/signUp.styles";

interface ProfileObject {
    id: number;
   name: string;
   age : number;
   city : string;
   status :  string;
   occupation : string;
    shortDescription: string;
    religion : string;
    divorced : boolean;
    kids : boolean;
    male : boolean;
    female : boolean;

}

 declare global {
     interface Window {
       ProfileData : ProfileObject;

     }
 }
export const EditProfile = (): JSX.Element => {
    const [profileData, setProfileData] = React.useState<string>("");
    const [id, setId] = React.useState<number>(0);
    const [profileId, setProfileId] = React.useState<number>(0);
    const [name, setName] = React.useState<string>("");
    const [age, setAge] = React.useState<number>(0);
    const [city, setCity] = React.useState<string>("");
    const [status, setStatus] = React.useState<string>("");
    const [occupation, setOccupation] = React.useState<string>("");
    const [shortDescription, setShortDescription] = React.useState<string>("");
    const [religion, setReligion] = React.useState<string>("");
    const [divorced, setDivorced] = React.useState<boolean>(false);
    const [kids, setKids] = React.useState<boolean>(false);
    const [male, setMale] = React.useState<boolean>(false);
    const [female, setFemale] = React.useState<boolean>(false);


    const [deletionSuccess, SetDeletionSuccess] = React.useState(false);

    useEffect(()=> {

       // const profile =  JSON.parse(localStorage.getItem("profile") || "");
        window.ProfileData = JSON.parse(localStorage.getItem("profile") || "");
        console.log(window.ProfileData);
        const user = JSON.parse(localStorage.getItem("user") || "");


       // console.log(user.profile.id);
        if(user != null){
           //console.log(profile);
          //  setProfileData(profile);
            //console.log(profileData);
            //console.log(user);
            setProfileId(window.ProfileData.id);
            console.log(profileId);
             setId(user.id);

              setName(window.ProfileData.name);
              setAge(window.ProfileData.age);
              setCity(window.ProfileData.city);
              setStatus(window.ProfileData.status);
              setOccupation(window.ProfileData.occupation);
              setShortDescription(window.ProfileData.shortDescription);
              setReligion(window.ProfileData.religion);
              setDivorced(window.ProfileData.divorced);
              setKids(window.ProfileData.kids);
              setMale(window.ProfileData.male);
              setFemale(window.ProfileData.female);
           // console.log(female);
           // setName(profile.name || "");


        }

    },[])


    const onChangeName = (event: any): void => {
        setName(event.target.value)
    }
    const onChangeAge = (event: any): void => {
        setAge(event.target.value)
    }

    const onChangeCity = (event: any): void => {
        setCity(event.target.value)
    }

    const onChangeStatus = (event: any): void => {
        setStatus(event.target.value)
    }

    const onChangeOccupation = (event: any): void => {
        setOccupation(event.target.value)
    }

    const onChangeShortD = (event: any): void => {
        setShortDescription(event.target.value)
    }

    const onChangeReligion = (event: any): void => {
        setReligion(event.target.value)
    }

    const onChangeDivorced = (event: any): void => {
        setDivorced(event.target.checked)
    }

    const onChangeKids = (event: any): void => {
        setKids(event.target.checked)
    }

    const onChangeMale = (event: any): void => {
        setMale(event.target.checked)
    }
    const onChangeFemale = (event: any): void => {
        setFemale(event.target.checked)
    }


    const Update = (event: any): void => {
        console.log(profileId);
        console.log(name);
        console.log(age);
        console.log(city);
        console.log(status);
        console.log(occupation);
        console.log(shortDescription);
        console.log(religion);
        console.log(divorced);
        console.log(kids);
        console.log(male);
        console.log(female);

       // const profileData = {id, name, age, city, status, occupation, shortDescription, religion,  divorced , kids, male, female};
        axios.put("http://localhost:8081/Profile/Update", {
            id:profileId,
            name:name,
            age:age,
            city:city,
            status:status,
            occupation:occupation,
            shortDescription: shortDescription,
            religion:  religion,
            divorced : divorced,
            kids : kids,
            male : male,
            female : female

        },
            {params: {id:id}}).catch((error: AxiosError) => {
            if (error.response) {
                console.error("Server Response Data:", error.response.data);
                console.error("Server Response Status:", error.response.status);
                console.error("Server Response Headers:", error.response.headers);
            } else {
                console.error("Error updating profile data:", error.message);
            }
        });
    }
    const Delete = (event: any): void => {
        console.log(id)
        axios.delete("http://localhost:8081/User/Delete", {
            params: { id: id }
        }).then(response => {
            console.log('Delete successful:', response);
            SetDeletionSuccess(true);
        }).catch(error => {
            console.error('Error deleting:', error);
        });
    }

    return <div style={{backgroundColor: '#E7EEC4', width: '1400px', height: '2000px'}}>
        {/* <div>
            <img
                style={pictureStyle1}
                src={require('./../../poze/wallpaper_two.jpg')}
            />

            <img
                style={pictureStyle2}
                src={require('./../../poze/wallpaper_two.jpg')}
            />


            <img
                style={pictureStyle3}
                src={require('./../../poze/wallpaper_two.jpg')}
            />


            <img
                style={pictureStyle4}
                src={require('./../../poze/wallpaper_two.jpg')}
            />


            <img
                style={pictureStyle5}
                src={require('./../../poze/wallpaper_two.jpg')}
            />

            <img
                style={pictureStyle6}
                src={require('./../../poze/wallpaper_two.jpg')}
            />

        </div>*/}
        <h3 style={writingStyle} >name</h3>
            <TextField style = {textFieldStyle} value={name}  variant="standard" onChange={onChangeName}
                       InputLabelProps={{
                           style: { color: '#485A11',  fontFamily: ' Montserrat' },
                       }}
            />
        <h3 style={writingStyle} >age</h3>
        <TextField style = {textFieldStyle} value={age}  variant="standard" onChange={onChangeAge}
                   InputLabelProps={{
                       style: { color: '#485A11',  fontFamily: ' Montserrat' },
                   }}
        />
        <h3 style={writingStyle} >city</h3>
        <TextField style = {textFieldStyle} value={city}  variant="standard" onChange={onChangeCity}
                   InputLabelProps={{
                       style: { color: '#485A11',  fontFamily: ' Montserrat' },
                   }}
        />
        <h3 style={writingStyle} >status</h3>
        <TextField
            style={{ ...textFieldStyle, width: '250px' }}
            value={status}
            onChange={onChangeStatus}
            multiline
            rows={2}
            InputLabelProps={{
                style: { color: '#485A11', fontFamily: 'Montserrat' },
            }}
        />
        <h3 style={writingStyle} >occupation</h3>
        <TextField style = {textFieldStyle} value={occupation}  variant="standard" onChange={onChangeOccupation}
                   InputLabelProps={{
                       style: { color: '#485A11',  fontFamily: ' Montserrat' },
                   }}
        />
        <h3 style={writingStyle} >short description</h3>
        <TextField
            style={{ ...textFieldStyle, width: '250px' }}
            value={shortDescription}
            onChange={onChangeShortD}
            multiline
            rows={3}
            InputLabelProps={{
                style: { color: '#485A11', fontFamily: 'Montserrat' },
            }}
        />
        <h3 style={writingStyle} >religion</h3>
        <TextField style = {textFieldStyle} value={religion}  variant="standard" onChange={onChangeReligion}
                   InputLabelProps={{
                       style: { color: '#485A11',  fontFamily: ' Montserrat' },
                   }}
        />
        <div style ={writingStyle2}>
            <div>
                <h3 style = {{position: "relative", left: -100, top:53}}>divorced</h3>
                <Checkbox

                    checked={divorced}
                    onChange = {onChangeDivorced}
                    sx={{
                        color: green[800],
                        '&.Mui-checked': {
                            color: green[600],
                        },
                    }}
                />
            </div>
            <div>
                <h3 style = {{position: "relative", left: -100, top:53}}>kids</h3>
                <Checkbox
                    checked={kids}
                    onChange = {onChangeKids}
                    sx={{
                        color: green[800],
                        '&.Mui-checked': {
                            color: green[600],
                        },
                    }}
                />
            </div>
            <div>
                <h3 style = {{position: "relative", left: -100, top:53}}>male</h3>
                <Checkbox

                    checked={male}
                    onChange = {onChangeMale}
                    sx={{
                        color: green[800],
                        '&.Mui-checked': {
                            color: green[600],
                        },
                    }}
                />
            </div>
            <div>
                <h3 style = {{position: "relative", left: -100, top:53}}>female</h3>
                <Checkbox
                    checked={female}
                    onChange = {onChangeFemale}
                    sx={{
                        color: green[800],
                        '&.Mui-checked': {
                            color: green[600],
                        },
                    }}
                />
            </div>
        </div>

        <Button style={updateButtonStyle} onClick={Update} variant="contained">Update</Button>
         <Button style={deleteButtonStyle} onClick={Delete} variant="contained">Delete my account</Button>
        {deletionSuccess && (
            <p style={{...deletionTagStyle,  color: 'green'} }>Account delleted!</p>
        )}
    </div>
}