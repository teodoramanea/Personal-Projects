import React, {useEffect, useState} from "react";
import {Button, TextField} from "@mui/material";
import {
    deleteButton, deletionTagStyle,
    insertButton,
    logoutButton,
    parentDivStyle,
    registrationTag, seeMatchesButton,
    updateButton
} from "../admin/admin.styles";
import { useNavigate } from "react-router-dom";

import { DataGrid, GridColDef, GridValueGetterParams } from '@mui/x-data-grid';
import axios, {AxiosError} from "axios";
import Box from '@mui/material/Box';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Divider from '@mui/material/Divider';


////user table
const columns: GridColDef[] = [
    { field: 'id', headerName: 'id', width: 70, editable: false },
    { field: 'admin', headerName: 'admin', width: 130, editable: true },
    { field: 'disableQuiz', headerName: 'disableQuiz', width: 130, editable: true },
    { field: 'name',headerName: 'name', width: 90, editable: true},
    { field: 'address', headerName: 'address', width: 180, editable: true },
    { field: 'telNumber', headerName: 'telNumber', width: 130, editable: true },
    { field: 'emailAddress', headerName: 'emailAddress', width: 180, editable: true },
    { field: 'password', headerName: 'password', width: 160, editable: true },

];

declare global {
    interface Window {
        userId : number[];
        match_user_ids : number[];
    }

}
function BasicList() {

    const [matchNames, setMatchNames] = React.useState<string[]>([]);
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
                    id: window.userId,
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
                                onDoubleClick={() => onShowDeleteOption(userIds[index])}
                            >

                                <ListItemText primary={name} />

                            </ListItemButton>

                            {showDeleteOption &&
                                <Button
                                    style={{color: '#485A11', fontFamily: 'Montserrat', left: 100}}
                                    onClick={() => onClickDeleteOption(userIds[index])}
                                >
                                    Delete match
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

  function DataTable() {

    const [rows1, setRows1] = React.useState([]);
    const [userId, setUserId] = React.useState<number>(0);
    const [selectedUserData, setSelectedUserData] = React.useState<any>([]);
    const [showMatches, setShowMatches] = React.useState<boolean>(false);


    const [name, setName] = React.useState<string>("");
    const [disableQuiz, setDisableQuiz] = React.useState<string>("");
    const [emailAddress, setEmailAddress] = React.useState<string>("");
    const [password, setPassword] = React.useState<string>("");
    const [address, setAddress] = React.useState<string>("");
    const [telNumber, setTelNumber] = React.useState<string>("")
    const admin = false;
    const [registrationSuccess, setRegistrationSuccess] = React.useState(false);
    const [deleteSuccess, setDeleteSuccess] = React.useState(false);
    const onChangeDisableQuiz = (event: any): void => {
        const enteredDisableQuiz = event.target.value;
        setDisableQuiz(enteredDisableQuiz);
        console.log(name);

    }
    const onChangeName = (event: any): void => {
        const enteredName = event.target.value;
        setName(enteredName);
        console.log(name);

    }
    const onChangeEmail = (event: any): void => {
        const enteredEmail = event.target.value;
        setEmailAddress(enteredEmail);
        console.log(emailAddress);
    }

    const onChangeAddress = (event: any): void => {
        const enteredAddress = event.target.value;
        setAddress(enteredAddress)
        console.log(address);

    }

    const onChangeTelNumber = (event: any): void => {
        const enteredTelNumber = event.target.value;
        setTelNumber(event.target.value)
        console.log(telNumber);

    }
    const onChangePassword = (event: any): void => {
        const enteredPassword = event.target.value;
        setPassword(enteredPassword)
        console.log(password);

    }

    useEffect(()=> {
        setRegistrationSuccess(false);
        setDeleteSuccess(false);
        axios.get("http://localhost:8081/User/ReadAll"
         ).then(response =>{
            //console.log(response.data);
            // rows1 = response.data;
             setRows1(response.data);

            // console.log(rows1);
         })

    },[])


    const handleRowClick = (params:any) => {
        //console.log('Selected row id:', params.row.id);
        setUserId(params.row.id);
        setSelectedUserData(Object.values(params.row));
        setShowMatches(false);
        window.userId = params.row.id;
    //    console.log(selectedUserData);

        axios.get("http://localhost:8081/Match/ReadMatchIds", {params : {id:params.row.id}}
        ).then(response => {
            //console.log(response.data);
            window.match_user_ids = response.data;
        }).catch(error => {
            console.error("Error fetching matchUserIds data:", error);
        });
    };

    const Update = () =>
    {
        console.log(userId);
        console.log(selectedUserData);
        setDeleteSuccess(false);
        setRegistrationSuccess(false);


        axios.put("http://localhost:8081/User/Update", {
                admin : selectedUserData[1],
                disableQuiz : selectedUserData[2],
                name : selectedUserData[3],
                address : selectedUserData[4],
                telNumber : selectedUserData[5],
                emailAddress : selectedUserData[6],
                password : selectedUserData[7]
            },
            {params : {id : userId}}).then(response => {
                console.log("intra aici?");
        })
            .catch(error => {
                console.error("Update failed", error);
            });

    }

    const Insert = () =>
    {
        setDeleteSuccess(false);
        const userData = { name, admin, disableQuiz, address, telNumber, emailAddress, password };
        axios.post("http://localhost:8081/User/Insert", userData)
        .then(response =>{

        }).catch(error => {
            console.error("Insert failed", error);
        })
        setRegistrationSuccess(true);
        console.log(registrationSuccess);
    }

    const Delete = () =>
    {
        setRegistrationSuccess(false);
        console.log(userId);
        axios.delete("http://localhost:8081/User/Delete", {params: {id : userId}})
            .then(response =>{
                setDeleteSuccess(true);
                console.log(deleteSuccess);
            }).catch(error => {
            console.error("Error deleting data!", error);
        })
    }

      const Show = () =>{
        if(showMatches)
        {
            setShowMatches(false);
        }else{
            setShowMatches(true);
        }
      }

    return (
        <div style={{position: "relative",  fontFamily: ' Montserrat'}}>
            <h1 style ={{color: '#485A11'}}>User Table</h1>
            <div style={{ height: 300, width: '100%' }}>
                <DataGrid
                    rows={rows1}
                    columns={columns}
                    // initialState={{
                    //     pagination: {
                    //         paginationModel: { page: 0, pageSize: 5 },
                    //     },
                    // }}
                    //pageSizeOptions={[10]}
                    onRowClick={handleRowClick}
                    //checkboxSelection
                />

                <Button style={updateButton} onClick={Update}>Update user</Button>
                <Button style={insertButton} onClick={Insert}>Insert user</Button>
                <Button style={deleteButton} onClick={Delete}>Delete user</Button>
                <Button style={seeMatchesButton} onClick={Show}>See user matches</Button>
                <div style={{position : 'relative', left : 760, top : 210}}>
                    { showMatches && <BasicList />}
                </div>

            </div>





            <div style={{ marginTop: 20, display: 'flex', flexDirection: 'row' }}>
                <div style={{ marginLeft: 20}}>
                    <TextField id="disableQuiz" label="Disable Quiz" variant="standard" onChange={onChangeDisableQuiz}
                               InputLabelProps={{
                                   style: { color: "black" ,  fontFamily: ' Montserrat'},
                               }}/>
                </div>
            <div style={{ marginLeft: 20}}>
                <TextField id="name" label="Name" variant="standard" onChange={onChangeName}
                           InputLabelProps={{
                               style: { color: "black" ,  fontFamily: ' Montserrat'},
                           }}/>
            </div>
            <div style={{ marginLeft: 20}}>
                <TextField id="address" label="address" variant="standard" onChange={onChangeAddress}
                           InputLabelProps={{
                               style: { color: "black" ,  fontFamily: ' Montserrat'},
                           }}
                />
            </div>
            <div style={{ marginLeft: 20}}>
                <TextField id="telNumber" label="phone number" variant="standard" onChange={onChangeTelNumber}
                           InputLabelProps={{
                               style: { color: "black" ,  fontFamily: ' Montserrat'},
                           }}/>
            </div>
            <div style={{ marginLeft: 20}}>
                <TextField id="emailAddress" label="email address" variant="standard" onChange={onChangeEmail}  InputLabelProps={{
                    style: { color: "black" ,  fontFamily: ' Montserrat'},
                }}/>
            </div>
            <div style={{ marginLeft: 20}}>
                <TextField id="password" label="password" variant="standard" onChange={onChangePassword}  InputLabelProps={{
                    style: { color: "black" ,  fontFamily: ' Montserrat'},
                }}/>
            </div>


                {registrationSuccess && (
                    <p style={{...registrationTag,  color: 'black'} }>User registered successfully!</p>
                )}

                {deleteSuccess && (
                    <p style={{...deletionTagStyle,  color: 'black'} }>User deleted successfully!</p>
                )}

            </div>

        </div>

    );
}


///profile table
///cand creezi un user se creeaza automat si un profil, si atunci cand stergi un user se sterge si un profil
const columns1: GridColDef[] = [
    { field: 'id', headerName: 'id', width: 70, editable: false },
    { field: 'name', headerName: 'name', width: 130, editable: true },
    { field: 'age', headerName: 'age', width: 130, editable: true },
    { field: 'city',headerName: 'city', width: 90, editable: true},
    { field: 'status', headerName: 'status', width: 100, editable: true },
    { field: 'occupation', headerName: 'occupation', width: 130, editable: true },
    { field: 'shortDescription', headerName: 'shortDescription', width: 180, editable: true },
    { field: 'religion', headerName: 'religion', width: 90, editable: true },
    { field: 'divorced',headerName: 'divorced', width: 90, editable: true},
    { field: 'kids',headerName: 'kids', width: 90, editable: true},
    { field: 'male',headerName: 'male', width: 90, editable: true},
    { field: 'female',headerName: 'female', width: 90, editable: true},
];

function DataTable1() {

    const [rows, setRows] = React.useState([]);
    const [userId, setUserId] = React.useState<number>(0);
    const [selectedProfileData, setSelectedProfileData] = React.useState<any>([]);


    useEffect(()=> {
        axios.get("http://localhost:8081/Profile/ReadAll"
        ).then(response =>{
            setRows(response.data);
            console.log(response.data);
        })


    },[])

    const handleRowClick = (params:any) => {
        console.log('Selected row id:', params.row.id);
        setSelectedProfileData(Object.values(params.row));
        //    console.log(selectedUserData);

        if(selectedProfileData[0] != null)
        {
            console.log(selectedProfileData[0]);
            axios.get("http://localhost:8081/Profile/findUserId", {params : {id:selectedProfileData[0]}}).then(response => {
                setUserId(response.data);
                console.log(response.data);
            })
        }

    };

    const Update = () =>
    {
        console.log(userId);
        console.log(selectedProfileData);

        axios.put("http://localhost:8081/Profile/Update", {
                id : selectedProfileData[0],
                name : selectedProfileData[1],
                age : selectedProfileData[2],
                city: selectedProfileData[3],
                status : selectedProfileData[4],
                occupation : selectedProfileData[5],
                shortDescription : selectedProfileData[6],
                religion : selectedProfileData[7],
                divorced : selectedProfileData[8],
                kids : selectedProfileData[9],
                male : selectedProfileData[10],
                female : selectedProfileData[11],

            },
            {params : {id : userId}}).then(response => {
            console.log("intra aici?");
        })
            .catch(error => {
                console.error("Update failed", error);
            });

    }

    return (
            <div style={{position: "relative",  fontFamily: ' Montserrat', top : 300}}>
                <h1 style ={{color: '#485A11'}}>Profile Table</h1>
                  <div style={{ height: 300, width: '100%' }}>
                    <DataGrid
                        rows={rows}
                        columns={columns1}
                        onRowClick={handleRowClick}
                        //checkboxSelection
                    />

                </div>
                <div style={{ marginTop: -140 }}>
                    <Button style={updateButton} onClick={Update}>Update profile</Button>
                </div>


            </div>

    );
}

///quizquestion table
const columns2: GridColDef[] = [
    { field: 'id', headerName: 'id', width: 70, editable: false },
    { field: 'question', headerName: 'question', width: 1000, editable: true },

    ]
function DataTable2() {
    const [rows, setRows] = React.useState([]);
    const [selectedQuestionData, setSelectedQuestionData] = React.useState<any>([]);
    const [registrationSuccess, setRegistrationSuccess] = React.useState(false);
    const [deleteSuccess, setDeleteSuccess] = React.useState(false);
    const [question, setQuestion] = React.useState<string>("");

    useEffect(()=> {
        axios.get("http://localhost:8081/QuizQuestion/ReadAlll"
        ).then(response =>{
            setRows(response.data);
            console.log(response.data);
        })


    },[])

    const handleRowClick = (params:any) => {
        console.log('Selected row id:', params.row.id);
        setSelectedQuestionData(Object.values(params.row));
        //    console.log(selectedUserData);
    };

    const onChangeQuestion = (event: any): void => {
        const enteredQuestion = event.target.value;
        setQuestion(enteredQuestion);
        //console.log(question);

    }
    const Update = () =>
    {
        console.log(selectedQuestionData);
        setDeleteSuccess(false);
        setRegistrationSuccess(false);


        axios.put("http://localhost:8081/QuizQuestion/Update", {
                question : selectedQuestionData[1],
            },
            {params : {id : selectedQuestionData[0]}}).then(response => {
            console.log("intra aici?");
        })
            .catch(error => {
                console.error("Update failed", error);
            });

    }

    const Insert = () =>
    {
        setDeleteSuccess(false);
        const questionData = { question };
        axios.post("http://localhost:8081/QuizQuestion/Insert", questionData)
            .then(response =>{

            }).catch(error => {
            console.error("Insert failed", error);
        })
        setRegistrationSuccess(true);
        console.log(registrationSuccess);
    }

    const Delete = () =>
    {
        setRegistrationSuccess(false);
        axios.delete("http://localhost:8081/QuizQuestion/Delete", {params: {id : selectedQuestionData[0]}})
            .then(response =>{
                setDeleteSuccess(true);
                console.log(deleteSuccess);
            }).catch(error => {
            console.error("Error deleting data!", error);
        })
    }


    return (
        <div style={{position: "relative",  fontFamily: ' Montserrat', top : 600}}>
            <h1 style ={{color: '#485A11'}}>Questions Table</h1>
            <div style={{ height: 300, width: '100%' }}>
                <DataGrid
                    rows={rows}
                    columns={columns2}
                    onRowClick={handleRowClick}
                    //checkboxSelection
                />

                <Button style={updateButton} onClick={Update}>Update question</Button>
                <Button style={insertButton} onClick={Insert}>Insert question</Button>
                <Button style={deleteButton} onClick={Delete}>Delete question</Button>

                {registrationSuccess && (
                    <p style={{position : "relative", top : 200, left : 300,  color: 'black'} }>Question added successfully!</p>
                )}

                {deleteSuccess && (
                    <p style={{position : "relative", top : 200, left : 500, color: 'black'} }>Question deleted successfully!</p>
                )}

                <div style={{ marginLeft: 50}}>
                    <TextField id="question" label="question" variant="standard" onChange={onChangeQuestion}
                               InputLabelProps={{
                                   style: { color: "black" ,  fontFamily: ' Montserrat'},
                               }}/>
                </div>
            </div>
        </div>
    )
}

///quizoption table
const columns3: GridColDef[] = [
    { field: 'id', headerName: 'id', width: 70, editable: false },
    { field: 'quizQuestion_id', headerName: 'question id', width: 100, editable: true },
    { field: 'answerOption', headerName: 'option', width: 1000, editable: true },


]
function DataTable3() {
    const [rows, setRows] = React.useState([]);
    const [selectedOptionData, setSelectedOptionData] = React.useState<any>([]);
    const [registrationSuccess, setRegistrationSuccess] = React.useState(false);
    const [deleteSuccess, setDeleteSuccess] = React.useState(false);
    const [option, setOption] = React.useState<string>("");
    const [questionId, setQuestionId] = React.useState<string>("");

    interface Row {
        id: number;
        quizQuestion: {
            id: number;
        };
        answerOption: string;
    }

    useEffect(()=> {
        axios.get("http://localhost:8081/QuizOption/ReadAlll")
            .then(response => {
                const updatedRows = response.data.map((row: Row)=> {
                    const questionId = row.quizQuestion?.id;
                    return { ...row, quizQuestion_id: questionId };
                });
                setRows(updatedRows);
            })
            .catch(error => {
                console.error("Error fetching quiz option data", error);
            });


    },[])

    const handleRowClick = (params:any) => {
        console.log('Selected row id:', params.row.id);
        setSelectedOptionData(Object.values(params.row));
        //    console.log(selectedUserData);
    };

    const onChangeOption = (event: any): void => {
        const enteredOption = event.target.value;
        setOption(enteredOption);
        //console.log(question);

    }
    const onChangeQuestionId = (event: any): void => {
        const enteredQuestionId = event.target.value;
        setQuestionId(enteredQuestionId);
        //console.log(question);

    }
    const Update = () =>
    {
        console.log(selectedOptionData);
        setDeleteSuccess(false);
        setRegistrationSuccess(false);


        axios.put("http://localhost:8081/QuizOption/Update", {
                answerOption : selectedOptionData[1],
            },
            {params : {id : selectedOptionData[0]}}).then(response => {
            console.log("intra aici?");
        })
            .catch(error => {
                console.error("Update failed", error);
            });

    }

    const Insert = () =>
    {
        console.log(questionId);
        console.log(option);
        setDeleteSuccess(false);
       //const optionData = {option};
        axios.post("http://localhost:8081/QuizOption/Insert", {answerOption : option}, {params : {id : questionId}})
            .then(response =>{
                setRegistrationSuccess(true);
                console.log(registrationSuccess);
            }).catch(error => {
            console.error("Insert failed", error);
        })

    }

    const Delete = () =>
    {
        setRegistrationSuccess(false);
        axios.delete("http://localhost:8081/QuizOption/Delete", {params: {id : selectedOptionData[0]}})
            .then(response =>{
                setDeleteSuccess(true);
                console.log(deleteSuccess);
            }).catch(error => {
            console.error("Error deleting data!", error);
        })
    }


    return (
        <div style={{position: "relative",  fontFamily: ' Montserrat', top : 1000}}>
            <h1 style ={{color: '#485A11'}}>Options Table</h1>
            <div style={{ height: 300, width: '100%' }}>
                <DataGrid
                    rows={rows}
                    columns={columns3}
                    onRowClick={handleRowClick}
                    //checkboxSelection
                />

                <Button style={updateButton} onClick={Update}>Update option</Button>
                <Button style={insertButton} onClick={Insert}>Insert option</Button>
                <Button style={deleteButton} onClick={Delete}>Delete option</Button>

                {registrationSuccess && (
                    <p style={{position : "relative", top : 210, left: 320, color: 'black'} }>Option added successfully!</p>
                )}

                {deleteSuccess && (
                    <p style={{position : "relative", top : 210, left : 500,  color: 'black'} }>Option deleted successfully!</p>
                )}

                <div style={{ marginTop: 20, display: 'flex', flexDirection: 'row' }}>
                <div style={{ marginLeft: 50}}>
                    <TextField id="id" label="Question Id" variant="standard" onChange={onChangeQuestionId}
                               InputLabelProps={{
                                   style: { color: "black" ,  fontFamily: ' Montserrat'},
                               }}/>
                </div>
                    <div style={{ marginLeft: 50}}>
                        <TextField id="option" label="option" variant="standard" onChange={onChangeOption}
                                   InputLabelProps={{
                                       style: { color: "black" ,  fontFamily: ' Montserrat'},
                                   }}/>
                    </div>
                </div>

            </div>
        </div>
    )
}

export const Admin = (): JSX.Element =>
{
    const navigate = useNavigate();

    const Logout = (event: any): void =>
    {
        navigate("/");
    }


    return <div style={parentDivStyle}>
        <Button style = {logoutButton} onClick={Logout} variant="contained">Log out</Button>

        <DataTable/>
        <DataTable1/>
        <DataTable2/>
        <DataTable3/>

    </div>
}