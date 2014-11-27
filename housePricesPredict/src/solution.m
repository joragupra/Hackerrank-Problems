params = sscanf(input('', 's'), '%d');
F = params(1);
N = params(2);
training = [];

for i = 1:N t = sscanf(input('', 's'), '%f'); training = [training; t']; endfor;

params = sscanf(input('', 's'), '%d');
T = params(1);

x_predict = [];
for i = 1:T x = sscanf(input('', 's'), '%f'); x_predict = [x_predict; x']; endfor;

X = training(:,1:F);
y = training(:,F+1);

theta = pinv(X'*X)*X'*y;

sol = x_predict * theta;

disp(sol);
