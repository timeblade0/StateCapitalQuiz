import random
import json
import os

# Get the script's directory path
script_dir = os.path.dirname(__file__)

# Load state capitals data from the JSON file
with open(os.path.join(script_dir, "state_capitals.json"), 'r') as file:
    state_capitals = json.load(file)

def quiz_user():
    states = list(state_capitals.keys())

    # Shuffle the order of states to make the quiz more interesting
    random.shuffle(states)

    for state in states:
        user_answer = input(f"What is the capital of {state}? ").strip()
        correct_answer = state_capitals[state]

        if user_answer.lower() == correct_answer.lower():
            print("Correct!\n")
        else:
            print(f"Wrong! The correct capital of {state} is {correct_answer}.\n")

### main ###
if __name__ == "__main__":
    os.system('cls')
    print("\nWelcome to the State Capitals Quiz!\n")
    quiz_user()
