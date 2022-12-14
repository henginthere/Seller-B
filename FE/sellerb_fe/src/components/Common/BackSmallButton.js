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
  `,
};

const StyledButton = styled.button`
  ${(p) => p.sizeStyle}

  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;

  border-radius: 0px;
  color: #132506;
  background-color: #e0d5ed;
  border: 0px none;
  margin-right: 10px;

  transition-duration: 400ms;
  transition-timing-function: ease;
  transition-property: transform;

  &:hover {
    transform: scale(1.04);
    background-color: #e9d9ee;
    cursor: pointer;
  }
`;

export const BackSmallButton = ({ disabled, size, label, onClick }) => {
  const sizeStyle = SIZE[size];

  return (
    <StyledButton
      disabled={disabled}
      sizeStyle={size}
      onClick={() => onClick()}
    >
      {label}
    </StyledButton>
  );
};

