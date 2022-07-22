import React from "react";
import Button from "@mui/material/Button";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";

function ManagerMainRight() {
  const styleObj = {
    width: "75%",
    border: "solid 2px black",
    borderRadius: "10px",
    margin: "10px",
  };
  const styleObj_center = {
    textAlign: "center",
    margin: "50px",
  };
  const styleObj_right = {
    display: "flex",
    justifyContent: "flex-end",
    marginRight: "100px",
  };
  return (
    <div style={styleObj}>
      <div style={styleObj_center}>ManagerMainRight</div>;
      <div style={styleObj_right}>
        <Button variant='contained'>상담사 관리</Button>
      </div>
      <div style={styleObj_center}>
        {/* 상담사 List */}
        <List sx={{ width: "100%" }}>
          <ListItem alignItems='center'>
            <ListItemText
              primary='상담사 이름'
              secondary='사번 or E-mail 등등...'
            ></ListItemText>
          </ListItem>
          <ListItem alignItems='center'>
            <ListItemText
              primary='상담사 이름'
              secondary='사번 or E-mail 등등...'
            ></ListItemText>
          </ListItem>
          <ListItem alignItems='center'>
            <ListItemText
              primary='상담사 이름'
              secondary='사번 or E-mail 등등...'
            ></ListItemText>
          </ListItem>
          <ListItem alignItems='center'>
            <ListItemText
              primary='상담사 이름'
              secondary='사번 or E-mail 등등...'
            ></ListItemText>
          </ListItem>
          <ListItem alignItems='center'>
            <ListItemText
              primary='상담사 이름'
              secondary='사번 or E-mail 등등...'
            ></ListItemText>
          </ListItem>
        </List>
      </div>
    </div>
  );
}
export default ManagerMainRight;
