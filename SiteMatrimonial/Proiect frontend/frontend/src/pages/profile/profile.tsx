import * as React from 'react';
import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";
import {logoutButton, parentDivStyle} from "./profile.styles";
import axios from "axios";
import {Button, Link} from "@mui/material";
import QuizIcon from '@mui/icons-material/Quiz';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import SvgIcon from '@mui/material/SvgIcon';

import AutoFixHighIcon from '@mui/icons-material/AutoFixHigh';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';

import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Divider from '@mui/material/Divider';
import InboxIcon from '@mui/icons-material/Inbox';
import DraftsIcon from '@mui/icons-material/Drafts';

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
        userid : number;
        disableQuiz : boolean;
        match_user_ids : number[];
        ProfileData : ProfileObject;

        whichProfile : boolean;
    }

}



 function BasicList() {

     const navigate = useNavigate();
     const [matchNames, setMatchNames] = useState<string[]>([]);
     const userIds = window.match_user_ids || [];
     const params = new URLSearchParams();
     const [showDeleteOption, setShowDeleteOption] = React.useState<boolean>(false);
     const [showDeleteMessage, setShowDeleteMessage] = React.useState<boolean>(false);

     useEffect(() => {
         setShowDeleteOption(false);
         setShowDeleteMessage(false);
     }, []);


     const onClickDeleteOption = (matchUserId: number): void => {
         console.log(window.userId);
         console.log(matchUserId);
         console.log(showDeleteMessage);
         axios
             .delete("http://localhost:8081/Match/Delete", {
                 params: {
                     id: window.userid,
                     match_user_id : matchUserId,

                 },
             })
             .then((response) => {
                 setShowDeleteMessage(true);
             })
             .catch((error) => {
                 console.error('Error deleting match:', error);
             });


     }

     const onShowDeleteOption = (matchUserId: number): void => {
         setShowDeleteOption(true);

     }

     const onMatchNameClick = (matchUserId: number): void => {
         console.log(matchUserId);
      //   localStorage.setItem("matchUserId", JSON.stringify(matchUserId));
        // navigate("/profile");

             axios
                 .get(`http://localhost:8081/Profile/Read`, {
                     params: {
                         id: matchUserId,
                     },
                 })
                 .then((response) => {
                     // console.log(response.data);
                     console.log(response.data.id);
                     window.ProfileData = response.data;
                     window.whichProfile = false;
                     console.log(window.ProfileData);
                     navigate("/profile");

                 })
                 .catch((error) => {
                     console.error('Error fetching match user profile data:', error);
                 });


     }

    // console.log(userIds);
     if(userIds.length != 0 && matchNames.length == 0)
     {
        // console.log(userIds);
         userIds.forEach((id) => {
             params.append('matchIds', id.toString());
         });

         const url = `http://localhost:8081/Profile/readAllProfileNames?${params.toString()}`;

         console.log(url);
         axios.get(url)
             .then(response =>{
             //    console.log(response.data);
                 setMatchNames(response.data);
             }).catch(error => {
             console.error("Error fetching profile names data", error);

         });

     }

    return (
        <Box sx={{ width: '80%', maxWidth: 130, bgcolor: '#EFF5D6' }}>
            <nav aria-label="main mailbox folders">
                <List>
                    {matchNames.map((name, index) => (
                        <ListItem key={index} disablePadding>
                            <ListItemButton
                                onClick={() => onMatchNameClick(userIds[index])}
                                onDoubleClick={() => onShowDeleteOption(userIds[index])}
                            >

                                <ListItemText primary={name} />

                            </ListItemButton>
                            {showDeleteOption &&
                                <Button
                                    style={{color: '#485A11', fontFamily: 'Montserrat', left: 100}}
                                    onClick={() => onClickDeleteOption(userIds[index])}
                                >
                                    Unmatch
                                </Button>
                            }

                            {showDeleteMessage && (
                                <p style={{ color: 'black', marginLeft: 200 }}>Match deleted successfully!</p>
                            )}
                        </ListItem>
                    ))}
                </List>
            </nav>
            <Divider />
        </Box>
    );
}



  function IconLabelTabs() {
    const [page, setPage] = React.useState(0);
    const navigate = useNavigate();
    const [quizCompleted, setQuizCompleted] = React.useState<boolean>(false);
    const [showMatches, setShowMatches] = React.useState<boolean>(false);

    useEffect(()=> {

         const quizCompletedData = JSON.parse(localStorage.getItem("quizComplete") || "false");
         console.log(quizCompletedData);
         console.log(quizCompleted);
        if (quizCompletedData != false && window.userid != null) {
            console.log(window.userid);
            axios.post("http://localhost:8081/User/DisableQuiz", null, {params : {
                id: window.userid, disableQuiz : quizCompletedData
            }}).then(response => {
               // setQuizCompleted(quizCompletedData);
               // console.log(quizCompleted);
            })
                .catch(error => {
                console.error("Error updating disableQuiz flag:", error);
            });

        }else {
            console.error("intra aici");
        }
    },[])

    const handleChange = (event: React.SyntheticEvent, newPage: number) => {
        setPage(newPage)
        if(newPage == 3)
        {
            navigate("/editProfile");
            setShowMatches(false);
        }
        else if(newPage == 2)
        {
            navigate("/QuizzPage");
            setShowMatches(false);
        }
        else if(newPage == 0)
        {
            navigate("/profile");
            window.whichProfile = true;
            setShowMatches(false);
        }
        else if(newPage == 1)
           setShowMatches(true);
    };

    return (
        <div>
         <Tabs value={page} onChange={handleChange} aria-label="icon label tabs example"   sx={{
             "& .MuiTabs-indicator": {
                 backgroundColor: '#485A11',
             },
         }}>
             <Tab label="My Profile" style={{ color: '#485A11' }} />
             <Tab  /*icon={<FavoriteBorderIcon />}*/ label="My matches" style = {{color : '#485A11'}} />
             <Tab /*icon={<QuizIcon />}*/ label="Take quiz" style = {{color : '#485A11'}} disabled={window.disableQuiz}/>

             <Tab /*icon={<AutoFixHighIcon />}*/  label="Edit my profile" style = {{color : '#485A11'}}/>

        </Tabs>
            <div style={{position : 'relative', left : 120}}>
                { showMatches && <BasicList />}
            </div>


        </div>
    );
}

export const Profile = (): JSX.Element => {


   // window.whichProfile = false;
    const [name, setName] = React.useState<string>("");
    const [age, setAge] = React.useState<string>("");
    const [city, setCity] = React.useState<string>("");
    const [status, setStatus] = React.useState<string>("");
    const [occupation, setOccupation] = React.useState<string>("");
    const [shortDescription, setShortDescription] = React.useState<string>("");
    const [religion, setReligion] = React.useState<string>("");
    const [divorced, setDivorced] = React.useState<boolean>(false);
    const [kids, setKids] = React.useState<boolean>(false);
    const [male, setMale] = React.useState<boolean>(false);
    const [female, setFemale] = React.useState<boolean>(false);
    const [userId, setUserId] = React.useState<number>(0);
    const [matchUserId, setMatchUserId] = React.useState<number>(0);

    const [matchProfile, setMatchProfile] = React.useState<number[]>([])

    const navigate = useNavigate();
   // console.log("caca");
    interface MatchObject {
        id: number;
        match_user_id: number;
    }

    useEffect(()=> {
       // console.log("pipi");

        //console.log(window.whichProfile);

        const user1 =  JSON.parse(localStorage.getItem("user") || "");

        if(user1 != null)
        {
            window.userid = user1.id;
            window.disableQuiz = user1.disableQuiz;

            console.log(user1.disableQuiz);
           axios.get("http://localhost:8081/Match/ReadMatchIds", {params : {id:user1.id}}
           ).then(response => {
             //  console.log(response.data);
               window.match_user_ids = response.data;
           }).catch(error => {
               console.error("Error fetching matchUserIds data:", error);
           });

           // console.log(matchUserIds);


            localStorage.setItem("user", JSON.stringify(user1));
            setUserId(user1.id);
            localStorage.setItem("quizComplete", JSON.stringify(false));
           // console.log(matchUserIds);


            // const matchId = JSON.parse(localStorage.getItem("matchUserId") || 'null');
            //
            // if (matchId != null) {
            //     setMatchUserId(matchId);
            //     console.log(matchId);
            //     //getProfileData(matchId);
            // } else {
            //
            // }
                getProfileData(user1.id);
        }

    },[window.whichProfile])


    const getProfileData = (userId: number): void =>{
            axios.get("http://localhost:8081/Profile/Read", {
            params:{
                 id : userId
            }
        }).then(response => {
          //  console.log(response.data.id);
            setName(response.data.name);
            setAge(response.data.age);
            setCity(response.data.city);
            setStatus(response.data.status);
            setOccupation(response.data.occupation);
            setShortDescription(response.data.shortDescription);
            setReligion(response.data.religion);
            setDivorced(response.data.divorced);
            setKids(response.data.kids);
            setMale(response.data.male);
            setFemale(response.data.female);
            window.ProfileData = response.data;
           // console.log(window.ProfileData.name);
            localStorage.setItem("profile", JSON.stringify(response.data));

           // console.log(response.data);

        }).catch(error => {
            console.error("Error fetching profile data:", error);
        });
    }

    const EditProfile = (event: any): void =>
    {
        navigate("/editProfile");
    }

    const Logout = (event: any): void =>
    {
        navigate("/");
    }


    return <div >
         <div>
            <img
                style={{
                    width: '900px',
                    height: '730px',
                    position: 'fixed',
                    top: 0,
                    left: 0,
                    opacity: 0.7,
                }}

                src={require('./../../poze/bck1.jpg')}
            />

             <img
                 style={{
                     width: '900px',
                     height: '730px',
                     position: 'fixed',
                     left: 900,
                     top: 0,
                     opacity: 0.7,
                 }}

                 src={require('./../../poze/bck1.jpg')}
             />
             </div>

         <h1 style={{
            fontSize: '4em', position: "relative", top : 50, left:100, color:'black',  fontFamily: ' Montserrat', fontWeight: 50}}>
             {window.ProfileData?.name} <span style={{ marginLeft: '0.5em' }}>{window.ProfileData?.age}</span>
         </h1>
          <h1 style ={{ position: "relative", top : 150, left:40, color:'black',  fontFamily: ' Montserrat', fontWeight: 50}}><b>About me</b></h1>
        {/*  <h1 style={{
            position: "relative", top : -95, left:320, color:'black',  fontFamily: ' Montserrat', fontWeight: 50}}>{window.ProfileData?.age}</h1>*/}

        <h2 style={{
            position: "relative", top : 160, left:70, color:'black',  fontFamily: ' Montserrat', fontWeight: 50}}>  <b>I live in..</b> {window.ProfileData?.city}</h2>
        <h2 style={{
           position: "relative", top : 160, left:70, color:'black',  fontFamily: ' Montserrat', fontWeight: 50}}><b>my purpose..</b> {window.ProfileData?.status}</h2>
        <h2 style={{
           position: "relative", top : 160, left:70, color:'black',  fontFamily: ' Montserrat', fontWeight: 50}}><b>my occupation is..</b> {window.ProfileData?.occupation}</h2>
       <h2 style={{
          position: "relative", top : 160, left:70, color:'black',  fontFamily: ' Montserrat', fontWeight: 50}}>{window.ProfileData?.shortDescription}</h2>

         <Button style = {logoutButton} onClick={Logout} variant="contained">Log out</Button>

        <div style={{ position: 'absolute', top: 70, right: 50 }}>
            <IconLabelTabs />
        </div>

    </div>

}