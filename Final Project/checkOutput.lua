-- Final Project Part 2 (LUA)
-- CS331
-- Daniel Roh


-- maxGuess()
-- Counts the amount of guesses per round to find the
-- max amount of guesses made per game
function maxGuess()
	local temp = 0
	local maxGuess = 0

	file = io.open(filename)
	
	--for line in file:lines() do
	while true do 
		line = file:read() --Read the next line
		
		if not line then --If the line doesnt exist
			break --Break while loop
		end
		
		if string.find(line, "matched") then  --If matched
			temp = temp + 1 --Incrment
		end
		
		if string.find(line, "turn") then --If matched
	       temp = temp + 1 --Incrment
		end
		
 		if string.find(line, "Wins") then --If a win is detected
			if maxGuess >= temp then --If the maxguess is more then current round
				temp = 0
			else --If maxGuess is less then current round
				mostGuess = temp --Replace current maxguess with current
				temp = 0 
			end
		end 
     end 
	 
     print("Most amount of guesses: " .. mostGuess)
end


-- minGuess()
-- Counts the least amount of guesses made in
-- in a game
function minGuesses()
	local temp = 0
	local minGuess = 100 --Set to 100 to alow change when run

	file = io.open(filename) --Open file
	
	while true do --While the file is bening read
		line = file:read() --Read the next line
		
		if not line then --If the line doesnt exist
			break --Break while loop
		end
		
     	if string.find(line, "turn") then --If found
     	   temp = temp + 1 --Incrment
		end
		
		if string.find(line, "matched") then --If found
			temp = temp + 1
		end
		
     	if string.find(line, "wins") then --If found
	         if minGuess <= temp then --If the current least is larger
     			  temp = 0 --Reset iterator
			else  --If a new smaller least is found
				minGuess = temp --Set new smallest
				temp = 0 --Reset iterator
			end
		end
    end 

       print ("Least amount of guesses: " .. minGuess)
end


-- avgGuesses()
-- Calculates the avgerage amount of guesses made 
-- when the program is run
function avgGuess()
	local avg
	local guess = 0

	file = io.open(filename) --Open file

	while true do --While file is being read
		line = file:read() --Read the next line
		
		if not line then --If the line doesnt exist
			break --Break while loop
		end
		
     	if string.find(line, "turn") then  --If found
			guess = guess + 1 --Incrment
	    end  

		if string.find(line, "matched") then --If found
			guess = guess + 1
		end
    end 

	avg = (guess / numGames) --Calculate avg
	print(string.format("The average number of guesses per game is: %.2f" , avg)) --Limit percision to 2 decimals
end 


-- avgMatches()
-- Counts the amount of matches made per game and takes
-- an avg of it
function avgMatches()
	local playerOneMatch = 0 
	local playerTwoMatch = 0
    local totalMatch = numGames * 26 --Calculate the total amount of matches, out of 26 matches per round
	
	file = io.open(filename) --Open file
	
		while true do --While file is being read
			line = file:read() --Read the next line
		
			if not line then --If the line doesnt exist
				break --Break while loop
			end
		
			if string.find(line, "Player 1 matched") then --If found
				playerOneMatch = playerOneMatch + 1 --Incrment
			end
			
			if string.find(line, "Player 2 matched") then --If found
				playerTwoMatch = playerTwoMatch + 1 --Incrment
		    end
		end 

	print(string.format("The average matches found by player 1: %.2f" , playerOneMatch/totalMatch)) --Limit precision to 2 decimals
	print(string.format("The average matches found by player 2: %.2f" , playerTwoMatch/totalMatch)) --Limit precision to 2 decimals
     
end


-- numOfGames()
-- Counts the amount of times the games have been run
-- recoards the wins per player
function totalGames()
	numGames = 0

	file = io.open(filename) --Open the file

	while true do 
		line = file:read() --Read the next line
		
		if not line then --If the line doesnt exist
			break --Break while loop
		end
	
		if string.find(line, "Wins") then -- If a player wins a game	
			numGames = numGames + 1  --Update counter
		end
		
		if string.find(line, "tied") then --If players tie a game
			numGames = numGames + 1
		end
	end
	
    print("Total number of games played: " .. numGames)	--Print out the result
end


-- playOfTheGame()
-- Finds the longest streak that a playere had
-- Funtion name has insperation from Overwatch
function playOfTheGame()
	local counter = 0
	local player1Streak = 0
	local player2Streak = 0
	local streak = 0
	
	file = io.open(filename)

	while true do 
		line = file:read() --Read the next line
		
		if not line then --If the line doesnt exist
			break --Break while loop
		end
		
		if string.find(line, "Player 1 matched") then --If player 1 finds a match
	        counter = counter + 1
		end
		
		if string.find(line, "Cards do not match") then --If player 1 turns end
			if counter >= player1Streak then --If the current value is larger then last
			    player1Streak = counter
				counter = 0 --reset counter
			else --If the value is not larger
				counter = 0 --reset counter
			end
		end	  
													    
		if string.find(line, "Player 2 matched") then --If player 2 finds a match
			counter = counter + 1 
		elseif string.find(line,  "No match found") then --If player 2 turns end
			if counter > player2Streak then --If the new streak is larger then current
				player2Streak = counter --Set new streak
				counter = 0 --Reset counter
			else --If the new streak is less then the current
				counter = 0 --Reset counter
			end
		end
	end

     if player1Streak > player2Streak then --If player 1 streak was bigger
		print("Longest streak was " .. player1Streak .. " matches by player 1")
     else --If player 2 streak was bigger
		print("Longest streak was " .. player2Streak .. " matches by player 2")
     end  
end



-- winnerWinnerChickenDinner()
-- Finds the winner of the game
-- Name inspired by player unknown battlegrounds
function winnerWinnerChickenDinner()
	local playerOne = 0
	local playerTwo = 0

	file = io.open(filename)
	
	while true do --While reading file
		line = file:read() --Read the next line
		
		if not line then --If the line doesnt exist
			break --Break while loop
		end

		if string.find(line, "Player 1 wins!") then --If player 1 won
			playerOne = playerOne + 1 --Incrment player 1 wins
    	elseif string.find(line, "Player 2 wins!") then --Of player 2 won
    	    playerTwo = playerTwo + 1  --Icrment player 2 wins
		end     
    end

    if playerOne > playerTwo then --If player 1 has more wins
       print ("Player 1 won the most games")
    elseif playerTwo > playerOne then --If player 2 has more wins
       print ("Player 2 won the most games")
    else --If the wins are the same for both
       print ("Player 1 and 2 tied in wins")
    end
end


--Function Call order
filename = "output.txt" --File to open

totalGames()
avgMatches()
maxGuess()
minGuesses()
avgGuess()
playOfTheGame()
winnerWinnerChickenDinner()

