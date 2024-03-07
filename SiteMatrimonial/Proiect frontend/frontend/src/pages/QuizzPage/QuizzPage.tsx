import * as React from 'react';
import axios from "axios";
import {useEffect} from "react";
import {
    optionStyle,
    parentDivStyle,
    questionStyle,
    quizNotCompleteTag,
    submitButtonStyle,
    submitTag
} from "./QuizzPage.styles";
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { green } from '@mui/material/colors';
import {Button} from "@mui/material";
import {AxiosError} from "axios/index";
import {registrationTag} from "../signUp/signUp.styles";


declare global {
    interface Window {
        options: string[];
        questions : string[];
        responses : number[];
        questionsCompleted : boolean[];
    }
}



export default function RadioButtonsGroup1() {
    const groups = [];
    const options = window.options || [];
    const questions = window.questions || [];
    const [responses, setResponses] = React.useState<number[]>([]);
    const [questionsCompleted, setQuestionsCompleted] = React.useState<boolean[]>(Array(questions.length).fill(false));
 //   const [index, setIndex] = React.useState<number>(0);
    const [prev_question_key, setPrevQuestionKey] = React.useState<number>(-1);

  //   var index = 0;

    const handleOptionChange = (question_key : number, option_index : number) => {
        const currentGroupIndex = question_key * 3;
        const new_option_index = currentGroupIndex + option_index;
        console.log("question_key:", question_key);
        if(prev_question_key == question_key)
        {
            responses[question_key] = new_option_index + 1;
            setResponses(responses);
            setQuestionsCompleted((prev) => {
                const updated = [...prev];
                updated[question_key] = true;
                return updated;
            });
        }else if(prev_question_key != question_key)
        {
            setQuestionsCompleted((prev) => {
                const updated = [...prev];
                updated[question_key] = true;
                return updated;
            });
            responses[question_key] = new_option_index + 1;
            setResponses(responses);

         //   console.log("index:", index_r);

        }

        setPrevQuestionKey(question_key);

        window.questionsCompleted = questionsCompleted;
        window.responses = responses;
        //console.log(new_option_index);
        console.log(window.responses);
        console.log(window.questionsCompleted);

    };
    //console.log(options);

    if (options.length >= 18) {
        // Împarte opțiunile în 8 grupuri de câte 3
        for (let i = 0; i < 18; i += 3) {
            const groupOptions = options.slice(i, i + 3);
            const group = (
                <FormControl key={`group-${i / 3}`}>
                    <RadioGroup
                    >
                        {groupOptions.map((option, index) => (
                            <FormControlLabel
                                key={index}
                                value={option}
                                control={<Radio sx={{
                                    color: green[800],
                                    '&.Mui-checked': {
                                        color: green[600],
                                    },
                                }} />}
                                label={option}
                                onChange={() => handleOptionChange(i / 3, index)}
                            />
                        ))}
                    </RadioGroup>
                </FormControl>
            );
            groups.push(group);
        }
    }
   return (
       <div>


              <h3 style={questionStyle}>{questions[0]}</h3>

           <div style={optionStyle}>
               {groups[0]}
           </div>

              <h3 style={questionStyle}>{questions[1]}</h3>
           <div style={optionStyle}>
               {groups[1]}
           </div>

           <h3 style={questionStyle}>{questions[2]}</h3>
           <div style={optionStyle}>
               {groups[2]}
           </div>

           <h3 style={questionStyle}>{questions[3]}</h3>
           <div style={optionStyle}>
               {groups[3]}
           </div>
            <h3 style={questionStyle}>{questions[4]}</h3>
           <div style={optionStyle}>
               {groups[4]}
           </div>
           <h3 style={questionStyle}>{questions[5]}</h3>
           <div style={optionStyle}>
               {groups[5]}

              </div>
       </div>
   );
}


export const QuizzPage = (): JSX.Element => {
    const [options, setOptions] = React.useState<string[]>([]);
    const responses = window.responses || [];
    const [quizCompleted, setQuizCompleted] = React.useState<boolean>(false);
    const [notAllCompleted, setNotAllCompleted] = React.useState<string>("");
    const params = new URLSearchParams();

    const [user_id, setUserId] = React.useState<number>(0);
    const [submitSuccess, setSubmitSuccess] = React.useState(false);

    useEffect(()=> {

        const user1 =  JSON.parse(localStorage.getItem("user") || "");
        if(user1 != null)
        {
        //    console.log(user1.id);
            //console.log(user1);
            setUserId(user1.id);
        }

    },[])



    useEffect(()=> {
            getQuizData();

    },[])

    const getQuizData = (): void =>{
        axios.get("http://localhost:8081/QuizQuestion/ReadAll"
        ) .then(response =>{
           //   console.log(response.data);
            window.questions = response.data;

        }).catch(error => {
            console.error("Error fetching quiz questions:", error);

        });

        axios.get("http://localhost:8081/QuizOption/ReadAll"
        ).then(response =>{
          //  console.log(response.data);
            setOptions(response.data);
            window.options = response.data;
         //   console.log(window.options[0]);

        }).catch(error => {
            console.error("Error fetching quiz options:", error);

        });

    }

    // useEffect(() => {
    //  //   console.log("Updated quizCompleted:", quizCompleted);
    //     localStorage.setItem("quizCompleted", JSON.stringify(quizCompleted || false));
    // }, [quizCompleted, submitSuccess]);

    const Submit = (event: any): void => {
        //console.log(user_id);
        //console.log(window.responses);
     //   console.log(quizCompleted);
        console.log(window.questionsCompleted);

        if(window.questionsCompleted){
        const responseData = window.responses.map(response => ({
            id_option: response
        }));
        console.log(responseData);


        axios.post("http://localhost:8081/QuizResponse/Insert?questionIds=1&questionIds=2&questionIds=3&questionIds=4&questionIds=5&questionIds=6", responseData,
      {
            params : {id: user_id,
            },

        }
        ).then(response => {
            console.log(response.data);
            setSubmitSuccess(true);
            setQuizCompleted(true);
             axios.post("http://localhost:8081/Match/Insert", null, {params : {id : user_id}}
                ).catch(error => {
                    console.error("Process failed", error);
                });
            //console.log(quizCompleted);
            localStorage.setItem("quizComplete", JSON.stringify(true));
        }) .catch(error => {
            console.error("Submit failed", error);
        });
        }else{
            setQuizCompleted(false);
            alert("Please complete all the questions before submitting.");
            setNotAllCompleted("Please complete all the questions!")
        }

      //  console.log(user_id);

    }
    return <div style={parentDivStyle}>

    <h2 style = {{position : "relative", top : 60, left :500,  fontFamily: ' Montserrat'}}> This month quiz</h2>

          <RadioButtonsGroup1  />
        <Button style={submitButtonStyle} onClick={Submit} variant="contained">Submit</Button>
        {submitSuccess && (
            <p style={{...submitTag,  color: '#485A11'} }>Be ready to find your love...</p>
        )}
        {quizCompleted && (
            <p style={{...quizNotCompleteTag,  color: '#485A11'} }>Please complete all the questions before submitting.</p>
        )}
    </div>

}