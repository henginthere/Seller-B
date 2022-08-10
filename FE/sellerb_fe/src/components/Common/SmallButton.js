import styled, { css } from "styled-components";

const SIZE = {
  sm: css`
  --button-font-size: 0.825rem;
  --button-padding: 8px 12px;
  --button-radius: 4px; 
  --button-width: 120px;
  --button-height: 30px;
  `,
  md: css`
  --button-font-size: 1rem;
  --button-padding: 12px 16px;
  --button-radius: 8px;
  --button-width: 148px;
  --button-height: 42px;
  `,
  lg: css`
  --button-font-size: 1.25rem;
  --button-padding: 16px 20px;
  --button-radius: 12px;
  `
}
// width: 148px;
// height: 42px;
const StyledButton = styled.button`
  ${(p) => p.sizeStyle}

  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;


  border-radius: 0px;
  color: #324E66;
  background-color: #E1EAFF;
  border: 0px none;
  margin-right: 10px;

`;

export const SmallButton = ({disabled, size, label, onClick}) => {
  console.log("받은 SIZE: " + size)

  // 상단 사이즈 리스트에서 해당하는 것 가져옴
  const sizeStyle= SIZE[size];

  return ( 
    <StyledButton disabled={disabled} sizeStyle={size} onClick={()=> onClick()}>
      {label}
    </StyledButton>
  );
}

// export default MyButton;