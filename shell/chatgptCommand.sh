function usage() {
  echo
  echo "############################## chatgpt user's guide ##############################"
  echo
  echo "We can use a total of 9 commands served by Open ai. \n"
  echo "Question ----> chatAsk -m \"question\" \n"
  echo "Remove previous question information(clear Session) ----> chatAsk -clear \n"
  echo "Grammar check ----> chatAsk -gc \"sentence\" \n"
  echo "Convert a voice file to a text file ----> chatAsk -at filename.m4 \n"
  echo "Sentence Emotion Analysis(Positive, Negative, Neutral) ----> chatAsk -md \"sentence\" \n"
  echo "Translate English into Korean ----> chatAsk -t \"english sentence\" \n"
  echo "Sentence Summary ----> chatAsk -s \"sentence\" \n"
  echo "User Lookup Top 5 Keywords Lookup ----> chatAsk -rank \n"
  echo "if you want a another service, send devygwan@gmail.com \n"
  echo "################################################################################# \n"
}

function chat_command() { 
  local SERVER_IP="localhost" 
  local SERVER_PORT="8081" 
  local REQUEST_PATH="$1" 
  local REQUEST_BODY="$2" 
 
  if [[ "$1" == "-help" || "$1" == "-m" || "$1" == "-md" || "$1" == "-gc" || "$1" == "-at" || "$1" == "-clear" || "$1" == "-t" || "$1" == "-s" || "$1" == "-rank" ]]; then 
    OPTION="$1" 
    shift 
  else 
    echo "Unknown option: $1" 
    echo "chatAsk instruction ---> chatAsk -help"
    return 1 
  fi 
  
  if [[ "$OPTION" == "-help" ]]; then 
    usage 
    return 1 
 
  elif [[ "$OPTION" == "-m" ]]; then 
    REQUEST_PATH="/v1/chat" 
  elif [[ "$OPTION" == "-md" ]]; then 
    REQUEST_PATH="/v1/mood" 
  elif [[ "$OPTION" == "-gc" ]]; then 
    REQUEST_PATH="/v1/gc" 
  elif [[ "$OPTION" == "-at" ]]; then 
    REQUEST_PATH="/v1/audio/transcriptions" 
  elif [[ "$OPTION" == "-clear" ]]; then 
    REQUEST_PATH="/v1/chat/reset" 
  elif [[ "$OPTION" == "-t" ]]; then 
    REQUEST_PATH="/v1/trans" 
  elif [[ "$OPTION" == "-s" ]]; then 
    REQUEST_PATH="/v1/summarize" 
  elif [[ "$OPTION" == "-rank" ]]; then 
    REQUEST_PATH="/v1/keywords" 
  fi 
 
  if [[ "$OPTION" == "-at" ]]; then 
    curl -X POST -H "Content-Type: multipart/form-data" -F file="${REQUEST_BODY}" "http://${SERVER_IP}:${SERVER_PORT}${REQUEST_PATH}" -w '\n' 
   
  elif [[ "$OPTION" == "-rank" || "$OPTION" == "-clear" ]]; then
    curl -X GET "http://${SERVER_IP}:${SERVER_PORT}${REQUEST_PATH}" -w '\n' 
   
  else 
    curl -X POST -H "Content-Type: text/plain" -d "$REQUEST_BODY" "http://${SERVER_IP}:${SERVER_PORT}${REQUEST_PATH}" -w '\n' 
  fi 
} 
 
# alias 설정
alias chatAsk='chat_command'

