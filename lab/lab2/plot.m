x=0:1:100;
y=0:1:100;
z=0:1:100;
[X,Y]=meshgrid(x,y);
for i=1:100
Z=i-X-Y;
mesh(X,Y,Z);
hold on;
end
xlabel('x');
ylabel('y');
zlabel('z');
zlim([0 100]);