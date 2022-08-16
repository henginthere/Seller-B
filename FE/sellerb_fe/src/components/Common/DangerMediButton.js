import styled, { css } from "styled-components";

const SIZE = {
  sm: css`
  --button-font-size: 0.825rem;
  --button-padding: 8px 12px;
  --button-radius: 4px; 
  `,
  md: css`
  --button-font-size: 1rem;
  --button-padding: 12px 16px;
  --button-radius: 8px;
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
  width: 148px;
  height: 42px;

  border-radius: 0px;
 color: #edefe9;
  background-color: #ff7d68;
  border: 0px none;
  margin-right: 10px;

  transition-duration: 400ms;
  transition-timing-function: ease;
  transition-property: transform;

  &:hover {
    transform: scale(1.04);
    background-color: #923c35;
    cursor: pointer;
  }



`;

export const DangerMediButton = ({disabled, size, label, onClick}) => {


  // 상단 사이즈 리스트에서 해당하는 것 가져옴
//   const sizeStyle= SIZE[size];

  return ( 
    <StyledButton disabled={disabled} sizeStyle={size} onClick={()=> onClick()}>
      {label}
    </StyledButton>
  );
}

// export default MyButton;